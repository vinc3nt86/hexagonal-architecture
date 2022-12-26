package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.EIDVApplicant;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.IdDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class CaptureIdDocumentFacade extends UseCaseExtension<EIDVApplicant, CaptureIdDocumentFacade.Dto> {
    private final UpdateApplicantDocumentUidUseCase updateApplicantDocumentUidUseCase;
    private final GetEIDVDocumentUseCase getEIDVDocumentUseCase;
    private final DownloadDocumentBinaryDataUseCase downloadDocumentBinaryDataUseCase;
    private final UploadDocumentToBucketUseCase uploadDocumentToBucketUseCase;

    @Override
    public EIDVApplicant execute(Dto dto) {
        log.info("Executing {} use-case", this.getClass().getName());

        String applicantId = updateApplicantDocumentIdInDB(dto);
        String base64Image = downloadDocumentBinaryData(applicantId, dto.getIdDocumentUid());

        //TODO: Should retrieve document object? It will contain file info like MimeType, issuing country (3 letter ISO) and filename
        IdDocument idDocument = getEIDVDocumentUseCase.execute(dto.getIdDocumentUid());
        idDocument.setBinaryData(IdDocument.decode(base64Image));

        uploadDocumentToBucket(applicantId, dto.getChannelApplicationId(), idDocument);

        EIDVApplicant eidvApplicant = new EIDVApplicant();
        eidvApplicant.setApplicantId(applicantId);
        eidvApplicant.setChannelApplicationId(dto.getChannelApplicationId());
        eidvApplicant.setIdDocumentId(dto.getIdDocumentUid());

        return eidvApplicant;
    }

    private void uploadDocumentToBucket(String applicantId, String channelApplicationId, IdDocument idDocument) {
        uploadDocumentToBucketUseCase.execute(UploadDocumentToBucketUseCase.Dto.builder()
                .applicantId(applicantId)
                .channelApplicationId(channelApplicationId)
                .idDocument(idDocument)
                .build());

        updateApplicantStatus(applicantId, EIDVApplicant.EIDVStatus.ID_DOCUMENT_UPLOADED);
    }

    private String downloadDocumentBinaryData(String applicantId, String documentId) {
        String base64String = downloadDocumentBinaryDataUseCase.execute(new DownloadDocumentBinaryDataUseCase.Dto(documentId));

        updateApplicantStatus(applicantId, EIDVApplicant.EIDVStatus.ID_DOCUMENT_DOWNLOADED);

        return base64String;
    }

    private String updateApplicantDocumentIdInDB(Dto dto) {
        String applicantId = updateApplicantDocumentUidUseCase.execute(UpdateApplicantDocumentUidUseCase.Dto.builder()
                .channelApplicationId(dto.getChannelApplicationId())
                .documentUid(dto.getIdDocumentUid())
                .documentType(IdDocument.DocumentType.ID_DOCUMENT)
                .build());

        updateApplicantStatus(applicantId, EIDVApplicant.EIDVStatus.ID_DOCUMENT_ID_UPDATED);

        return applicantId;
    }

    @Getter
    @Builder
    public static class Dto {
        private final String channelApplicationId;
        private final String idDocumentUid;
    }
}
