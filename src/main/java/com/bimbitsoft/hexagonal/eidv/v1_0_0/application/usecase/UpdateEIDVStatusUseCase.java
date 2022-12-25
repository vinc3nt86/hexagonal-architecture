package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.EIDVApplicant;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out.EIDVApplicantRepositoryPort;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out.EIDVAuditRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
class UpdateEIDVStatusUseCase implements UseCase<String, UpdateEIDVStatusUseCase.Dto> {
    private EIDVApplicantRepositoryPort eidvApplicantRepositoryPort;
    private EIDVAuditRepository eidvAuditRepository;

    @Override
    public String execute(UpdateEIDVStatusUseCase.Dto updateEIDVStatusDto) {
        log.info("Executing {} use-case", this.getClass().getName());

        eidvApplicantRepositoryPort.updateApplicantStatus(updateEIDVStatusDto.getApplicantId(), updateEIDVStatusDto.getStatus().name());
        eidvAuditRepository.addApplicantStatusRecord(updateEIDVStatusDto.getApplicantId(), updateEIDVStatusDto.getStatus().toString());
        //TODO: Sending audit info to ADA through Kafka Topic
        return updateEIDVStatusDto.getStatus().name();
    }

    @Getter
    @Builder
    static class Dto {
        private String applicantId;
        private EIDVApplicant.EIDVStatus status;
    }
}
