package com.bimbitsoft.hexagonal.application.casa.v1_0_0.core;

import com.bimbitsoft.hexagonal.application.casa.v1_0_0.core.error.CasaOnboardingErrorCode;
import com.bimbitsoft.hexagonal.application.casa.v1_0_0.core.port.in.CasaSessionManagerService;
import org.springframework.stereotype.Service;

@Service
public class CasaSessionManagerServiceImpl implements CasaSessionManagerService {
    @Override
    public void createSession() {
        //Test Code
        CasaOnboardingErrorCode.DUPLICATION.HAS_EXISTING_LIVE_PRODUCT.getValue().getCode();
        CasaOnboardingErrorCode.DUPLICATION.HAS_EXISTING_LIVE_PRODUCT.getValue().getMessage();
    }

    @Override
    public void validateSameSession() {
    }
}
