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
class CreateNewApplicantUseCase implements UseCase<String, CreateNewApplicantUseCase.Dto> {
    private final EIDVManagerPort eidvManagerPort;
    private final EIDVApplicantRepositoryPort eidvApplicantRepositoryPort;

    @Override
    public String execute(Dto dto) {
        log.info("Executing {} use-case", this.getClass().getName());

        return eidvApplicantRepositoryPort.findApplicantIdByChannelApplicationId(dto.getChannelApplicationId())
                .orElseGet(() -> {
                    String newApplicantId = createNewApplicant(dto.getFirstName(), dto.getLastName());
                    saveApplicantIdInDB(newApplicantId, dto.getChannelApplicationId());
                    return newApplicantId;
                });
    }

    private String createNewApplicant(String firstName, String lastName) {
        log.info("Attempt to create new applicant");
        return eidvManagerPort.createApplicant(firstName, lastName);
    }

    private void saveApplicantIdInDB(String applicantId, String channelApplicationId) {
        log.info("Attempt to save new applicant id {} into database for {} application", applicantId, channelApplicationId);
        eidvApplicantRepositoryPort.saveApplicantId(applicantId, channelApplicationId);
    }

    @Builder
    @Getter
    static class Dto {
        private final String channelApplicationId;
        private final String firstName;
        private final String lastName;
    }
}
