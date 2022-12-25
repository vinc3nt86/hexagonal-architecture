package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EIDVApplicant {
    private String applicantId;
    private String channelApplicationId;
    private String sdkToken;
    private String idDocumentId;
    private String liveVideoId;
    private String checkResultReferenceId;
    private String deleteStatus;
    private LocalDateTime deletedTime;
    private String status;
    private String iwfCaseId;

    public enum EIDVStatus {
        APPLICANT_CREATED, SDK_TOKEN_GENERATED,
        ID_DOCUMENT_ID_UPDATED, ID_DOCUMENT_UPLOADED, ID_DOCUMENT_DOWNLOADED,
        LIVE_VIDEO_UPLOADED, LIVE_VIDEO_FRAME_DOWNLOADED, LIVE_VIDEO_ID_UPDATED
    }
}
