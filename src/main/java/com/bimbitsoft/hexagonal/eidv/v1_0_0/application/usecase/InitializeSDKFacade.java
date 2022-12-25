package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.domain.CustomerDetail;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.domain.EIDVApplicant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class InitializeSDKFacade implements UseCase<EIDVApplicant, InitializeSDKFacade.Dto> {
    private final CreateNewApplicantUseCase createNewApplicantUseCase;
    private final GenerateSDKTokenUseCase generateSDKTokenUseCase;
    private final GetCustomerDetailFromDBUseCase getCustomerDetailFromDBUseCase;
    private final UpdateEIDVStatusUseCase updateEIDVStatusUseCase;

    @Override
    public EIDVApplicant execute(Dto dto) {
        log.info("Executing {} use-case", this.getClass().getName());

        CustomerDetail customerDetail = getCustomerDetailFromDBUseCase.execute(dto.getChannelApplicationId());

        CreateNewApplicantUseCase.Dto createNewApplicantDto = CreateNewApplicantUseCase.Dto.builder()
                .channelApplicationId(dto.getChannelApplicationId())
                .firstName(customerDetail.getFirstName())
                .lastName(customerDetail.getLastName())
                .build();
        String applicantId = createNewApplicantUseCase.execute(createNewApplicantDto);

        updateApplicantStatus(applicantId, EIDVApplicant.EIDVStatus.APPLICANT_CREATED);

        String sdkToken = generateSDKTokenUseCase.execute(GenerateSDKTokenDto.builder().applicantId(applicantId).build());

        updateApplicantStatus(applicantId, EIDVApplicant.EIDVStatus.SDK_TOKEN_GENERATED);

        EIDVApplicant eidvApplicant = new EIDVApplicant();
        eidvApplicant.setApplicantId(applicantId);
        eidvApplicant.setSdkToken(sdkToken);
        eidvApplicant.setChannelApplicationId(dto.getChannelApplicationId());

        return eidvApplicant;
    }

    private String updateApplicantStatus(String applicantId, EIDVApplicant.EIDVStatus status) {
        return updateEIDVStatusUseCase.execute(UpdateEIDVStatusUseCase.Dto.builder()
                .applicantId(applicantId)
                .status(status)
                .build());
    }

    @Getter
    @AllArgsConstructor
    public static class Dto {
        private String channelApplicationId;
    }
}
