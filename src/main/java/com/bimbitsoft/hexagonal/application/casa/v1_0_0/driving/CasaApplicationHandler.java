package com.bimbitsoft.hexagonal.application.casa.v1_0_0.driving;

import com.bimbitsoft.hexagonal.application.casa.v1_0_0.core.port.in.CasaApplicationService;
import com.bimbitsoft.hexagonal.application.casa.v1_0_0.core.port.in.CasaSessionManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CasaApplicationHandler {
    //Ports
    private CasaSessionManagerService casaSessionManagerService;
    private CasaApplicationService casaApplicationService;
    //Components
    private Mapper mapper;

    public void createApplication() {

    }

    public void updateApplication() {

    }

    public void getApplication() {

    }

    public void getEligibleProductBundles() {

    }
}
