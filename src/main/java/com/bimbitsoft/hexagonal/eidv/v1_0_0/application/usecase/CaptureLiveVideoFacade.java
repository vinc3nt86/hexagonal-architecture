package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.EIDVApplicant;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.EIDVDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class CaptureLiveVideoFacade extends UseCaseExtension<EIDVApplicant, CaptureLiveVideoFacade.Dto> {
    private UpdateApplicantDocumentIdUseCase updateApplicantDocumentIdUseCase;
    private DownloadDocumentBinaryDataUseCase downloadDocumentBinaryDataUseCase;
    private UploadDocumentToBucketUseCase uploadDocumentToBucketUseCase;

    @Override
    public EIDVApplicant execute(CaptureLiveVideoFacade.Dto dto) {
        log.info("Executing {} use-case", this.getClass().getName());

        EIDVApplicant eidvApplicant = updateApplicantDocumentIdInDB(dto);
        String base64Image = downloadDocumentBinaryData(eidvApplicant.getApplicantId(), dto.getLiveVideoId());
        uploadDocumentToBucket(eidvApplicant.getApplicantId(), dto.getChannelApplicationId(), base64Image);

        return null;
    }

    private EIDVApplicant updateApplicantDocumentIdInDB(CaptureLiveVideoFacade.Dto dto) {
        EIDVApplicant eidvApplicant = updateApplicantDocumentIdUseCase.execute(UpdateApplicantDocumentIdUseCase.Dto.builder()
                .channelApplicationId(dto.getChannelApplicationId())
                .documentId(dto.getLiveVideoId())
                .documentType(EIDVDocument.DocumentType.ID_DOCUMENT)
                .build());

        updateApplicantStatus(eidvApplicant.getApplicantId(), EIDVApplicant.EIDVStatus.LIVE_VIDEO_ID_UPDATED);

        return eidvApplicant;
    }

    private void uploadDocumentToBucket(String applicantId, String channelApplicationId, String base64Image) {
        uploadDocumentToBucketUseCase.execute(UploadDocumentToBucketUseCase.Dto.builder()
                .applicantId(applicantId)
                .base64String(base64Image)
                .channelApplicationId(channelApplicationId)
                .documentType(EIDVDocument.DocumentType.LIVE_VIDEO)
                .build());

        updateApplicantStatus(applicantId, EIDVApplicant.EIDVStatus.LIVE_VIDEO_UPLOADED);
    }

    private String downloadDocumentBinaryData(String applicantId, String documentId) {
        String base64String = downloadDocumentBinaryDataUseCase.execute(new DownloadDocumentBinaryDataUseCase.Dto(documentId));

        updateApplicantStatus(applicantId, EIDVApplicant.EIDVStatus.LIVE_VIDEO_FRAME_DOWNLOADED);

        return base64String;
    }

    @Getter
    @Builder
    public static class Dto {
        private final String channelApplicationId;
        private final String liveVideoId;
    }
}
