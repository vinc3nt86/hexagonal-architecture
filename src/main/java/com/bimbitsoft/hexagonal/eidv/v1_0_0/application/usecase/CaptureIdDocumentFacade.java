package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.domain.EIDVApplicant;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.domain.EIDVDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class CaptureIdDocumentFacade implements UseCase<EIDVApplicant, CaptureIdDocumentFacade.Dto> {
    private UpdateApplicantDocumentIdUseCase updateApplicantDocumentIdUseCase;
    private DownloadDocumentBinaryDataUseCase downloadDocumentBinaryDataUseCase;
    private UploadDocumentToBucketUseCase uploadDocumentToBucketUseCase;
    private UpdateEIDVStatusUseCase updateEIDVStatusUseCase;

    @Override
    public EIDVApplicant execute(Dto dto) {
        log.info("Executing {} use-case", this.getClass().getName());

        EIDVApplicant eidvApplicant = updateApplicantDocumentIdInDB(dto);
        String base64Image = downloadDocumentBinaryData(eidvApplicant.getApplicantId(), dto.getDocumentId());
        uploadDocumentToBucket(eidvApplicant.getApplicantId(), dto.getChannelApplicationId(), base64Image);

        return eidvApplicant;
    }

    private void uploadDocumentToBucket(String applicantId, String channelApplicationId, String base64Image) {
        uploadDocumentToBucketUseCase.execute(UploadDocumentToBucketUseCase.Dto.builder()
                .applicantId(applicantId)
                .base64String(base64Image)
                .channelApplicationId(channelApplicationId)
                .documentType(EIDVDocument.DocumentType.ID_DOCUMENT)
                .build());

        updateApplicantStatus(applicantId, EIDVApplicant.EIDVStatus.ID_DOCUMENT_UPLOADED);
    }

    private String downloadDocumentBinaryData(String applicantId, String documentId) {
        String base64String = downloadDocumentBinaryDataUseCase.execute(new DownloadDocumentBinaryDataUseCase.Dto(documentId));

        updateApplicantStatus(applicantId, EIDVApplicant.EIDVStatus.ID_DOCUMENT_DOWNLOADED);

        return base64String;
    }

    private EIDVApplicant updateApplicantDocumentIdInDB(Dto dto) {
        EIDVApplicant eidvApplicant = updateApplicantDocumentIdUseCase.execute(UpdateApplicantDocumentIdUseCase.Dto.builder()
                .channelApplicationId(dto.getChannelApplicationId())
                .documentId(dto.getDocumentId())
                .documentType(EIDVDocument.DocumentType.ID_DOCUMENT)
                .build());

        updateApplicantStatus(eidvApplicant.getApplicantId(), EIDVApplicant.EIDVStatus.ID_DOCUMENT_UPDATED);

        return eidvApplicant;
    }

    private String updateApplicantStatus(String applicantId, EIDVApplicant.EIDVStatus status) {
        return updateEIDVStatusUseCase.execute(UpdateEIDVStatusUseCase.Dto.builder()
                .applicantId(applicantId)
                .status(status)
                .build());
    }

    @Getter
    @Builder
    public static class Dto {
        private final String channelApplicationId;
        private final String documentId;
    }
}
