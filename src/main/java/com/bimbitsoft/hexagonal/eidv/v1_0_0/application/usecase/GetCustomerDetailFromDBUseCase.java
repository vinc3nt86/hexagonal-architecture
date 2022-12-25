package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.domain.CustomerDetail;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.exception.EIDVCoreException;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out.CustomerDetailRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
class GetCustomerDetailFromDBUseCase implements UseCase<CustomerDetail, String> {
    private final CustomerDetailRepositoryPort customerDetailRepositoryPort;

    @Override
    public CustomerDetail execute(String applicationId) {
        return customerDetailRepositoryPort.findCustomerDetailsByApplicationId(applicationId)
                .orElseThrow(() -> {
                    log.error("Customer details is not found for prospect with prospect id {}", applicationId);
                    throw new EIDVCoreException(EIDVCoreException.ExceptionCode.SERVER_ERROR);
                });
    }
}
