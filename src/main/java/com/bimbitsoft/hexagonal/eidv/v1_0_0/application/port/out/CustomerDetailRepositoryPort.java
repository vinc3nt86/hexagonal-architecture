package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.domain.CustomerDetail;

import java.util.Optional;

public interface CustomerDetailRepositoryPort {
    Optional<CustomerDetail> findCustomerDetailsByApplicationId(String applicationId);
}
