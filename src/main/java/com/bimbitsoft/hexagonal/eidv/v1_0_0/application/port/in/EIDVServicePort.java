package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.in;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.domain.EIDVApplicant;

public interface EIDVServicePort {
    EIDVApplicant createNewApplicant(EIDVApplicant eidvApplicant);
    EIDVApplicant updateApplicant(EIDVApplicant eidvApplicant);
}
