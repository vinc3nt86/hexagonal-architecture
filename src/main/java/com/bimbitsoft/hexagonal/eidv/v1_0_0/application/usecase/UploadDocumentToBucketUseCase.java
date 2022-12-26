package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.IdDocument;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.UploadedDocumentRecord;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out.FileManagerPort;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out.CustomerUploadedDocumentRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
class UploadDocumentToBucketUseCase implements UseCase<String, UploadDocumentToBucketUseCase.Dto> {
    private final CustomerUploadedDocumentRepositoryPort customerUploadedDocumentRepositoryPort;
    private final FileManagerPort fileManagerPort;

    @Override
    public String execute(Dto dto) {
        log.info("Executing {} use-case", this.getClass().getName());

        IdDocument idDocument = dto.getIdDocument();
        String formattedFilename = fileManagerPort.generateFilename(
                dto.getChannelApplicationId(),
                dto.getApplicantId(),
                idDocument
        );

        String resourceFilePath = fileManagerPort.createResourceFilePath(idDocument.getBinaryData());
        fileManagerPort.scanForVirus(resourceFilePath);
        fileManagerPort.upload(resourceFilePath);
        fileManagerPort.deleteResourceFile(resourceFilePath);

        customerUploadedDocumentRepositoryPort.findByApplicationIdAndDocumentType(dto.getChannelApplicationId(), idDocument.getType())
                .ifPresentOrElse(
                        uploadedDocumentRecord -> updateUploadedDocumentRecordInDB(uploadedDocumentRecord, dto.getChannelApplicationId()),
                        () -> addUploadedDocumentRecordInDB(new UploadedDocumentRecord(), dto.getChannelApplicationId()));

        return formattedFilename;
    }

    private void addUploadedDocumentRecordInDB(UploadedDocumentRecord uploadedDocumentRecord, String applicationId) {
        customerUploadedDocumentRepositoryPort.add(uploadedDocumentRecord, applicationId);
    }

    private void updateUploadedDocumentRecordInDB(UploadedDocumentRecord uploadedDocumentRecord, String applicationId) {
        customerUploadedDocumentRepositoryPort.update(uploadedDocumentRecord, applicationId);
    }

    private String generateFilename(String channelApplicationId, String applicantId, IdDocument.DocumentType documentType) {
        // Format: <DOC_Country>-<PROSPECT_ID>-<DOC_TYPE>-<DOC_MimeType>-<SysTimeMillis>.<Ext/MimeType>
        // MimeType == Ext isn't it ???
        return "";
    }

    @Getter
    @Builder
    static class Dto {
        private String channelApplicationId;
        private String applicantId;
        private IdDocument idDocument;
    }
}
