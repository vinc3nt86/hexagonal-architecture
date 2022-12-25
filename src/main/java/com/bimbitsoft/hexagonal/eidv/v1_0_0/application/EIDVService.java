package com.bimbitsoft.hexagonal.eidv.v1_0_0.application;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.domain.EIDVApplicant;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.in.EIDVServicePort;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase.InitializeSDKFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EIDVService implements EIDVServicePort {
    private InitializeSDKFacade initializeSDKFacade;

    @Override
    public EIDVApplicant createNewApplicant(EIDVApplicant eidvApplicant) {
        return initializeSDKFacade.execute(new InitializeSDKFacade.Dto(eidvApplicant.getChannelApplicationId()));
    }

    @Override
    public EIDVApplicant updateApplicant(EIDVApplicant eidvApplicant) {
        return null;
    }
}
