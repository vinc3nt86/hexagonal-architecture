package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.EIDVDocument;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out.UploadedDocumentRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
class UploadDocumentToBucketUseCase implements UseCase<String, UploadDocumentToBucketUseCase.Dto> {
    private UploadedDocumentRepositoryPort uploadedDocumentRepositoryPort;


    @Override
    public String execute(UploadDocumentToBucketUseCase.Dto dto) {
        log.info("Executing {} use-case", this.getClass().getName());

        scanDocumentForVirus();
        String filename = generateFilename();
        uploadDocumentToBucket();
        addUploadedDocumentRecordInDB();

        return filename;
    }

    private boolean scanDocumentForVirus() {
        return false;
    }

    private void uploadDocumentToBucket() {

    }

    private void addUploadedDocumentRecordInDB() {

    }

    private String generateFilename() {
        return null;
    }

    @Getter
    @Builder
    static class Dto {
        private String channelApplicationId;
        private String base64String;
        private String applicantId;
        private EIDVDocument.DocumentType documentType;
    }
}
