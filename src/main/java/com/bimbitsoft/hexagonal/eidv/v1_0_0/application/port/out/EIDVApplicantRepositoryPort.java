package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.domain.EIDVApplicant;

import java.util.Optional;

public interface EIDVApplicantRepositoryPort {
    void saveApplicantId(String applicantId, String channelApplicationId);

    Optional<String> findApplicantIdByChannelApplicationId(String channelApplicationId);

    void updateApplicantStatus(String applicantId, String status);

    Optional<String> findSDKTokenByApplicantId(String applicantId);

    void updateSDKToken(String sdkToken, String applicantId);
}