package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.EIDVApplicant;

public abstract class UseCaseExtension<T, U> implements UseCase<T, U> {
    private UpdateEIDVStatusUseCase updateEIDVStatusUseCase;

    protected String updateApplicantStatus(String applicantId, EIDVApplicant.EIDVStatus status) {
        return updateEIDVStatusUseCase.execute(UpdateEIDVStatusUseCase.Dto.builder()
                .applicantId(applicantId)
                .status(status)
                .build());
    }
}
