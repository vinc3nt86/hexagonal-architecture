package com.bimbitsoft.hexagonal.eidv.v1_0_0.application;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.EIDVApplicant;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.in.EIDVServicePort;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase.CaptureIdDocumentFacade;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase.CaptureLiveVideoFacade;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase.InitializeSDKFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EIDVService implements EIDVServicePort {
    private final InitializeSDKFacade initializeSDKFacade;
    private final CaptureIdDocumentFacade captureIdDocumentFacade;
    private final CaptureLiveVideoFacade captureLiveVideoFacade;

    @Override
    public EIDVApplicant createNewApplicant(EIDVApplicant eidvApplicant) {
        //TODO: call to customer/form domain to retrieve customer info from DB.
        // (customer can either be refer to domain to process customer detail in subsystem only / provide a use case to extract value from db)
        // (form can be refer to the domain that manager everything relate to this form application)
        return initializeSDKFacade.execute(new InitializeSDKFacade.Dto(eidvApplicant.getChannelApplicationId()));
    }

    @Override
    public EIDVApplicant updateApplicant(EIDVApplicant eidvApplicant) {
        return null;
    }

    @Override
    public EIDVApplicant extractIdDocumentOCR(EIDVApplicant eidvApplicant) {
        return null;
    }
}
