package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out.EIDVApplicantRepositoryPort;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out.EIDVManagerPort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
class GenerateSDKTokenUseCase implements UseCase<String, GenerateSDKTokenUseCase.Dto> {
    private EIDVManagerPort eidvManagerPort;
    private EIDVApplicantRepositoryPort eidvApplicantRepositoryPort;

    @Override
    public String execute(GenerateSDKTokenUseCase.Dto dto) {
        log.info("Executing {} use-case", this.getClass().getName());

        return eidvApplicantRepositoryPort.findSDKTokenByApplicantId(dto.getApplicantId())
                .orElseGet(() -> {
                    String sdkToken = eidvManagerPort.generateSDKToken(dto.getApplicantId());
                    saveSDKTokenInDB(sdkToken, dto.getApplicantId());
                    return sdkToken;
                });
    }

    private void saveSDKTokenInDB(String sdkToken, String applicantId) {
        eidvApplicantRepositoryPort.updateSDKToken(sdkToken, applicantId);
    }

    @Getter
    @Builder
    static class Dto {
        private String applicantId;
    }
}
