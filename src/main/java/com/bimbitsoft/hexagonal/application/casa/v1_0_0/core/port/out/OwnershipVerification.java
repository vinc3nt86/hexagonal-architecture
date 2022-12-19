package com.bimbitsoft.hexagonal.application.casa.v1_0_0.core.port.out;

public interface OwnershipVerification {
    void generateOTP();
    void verifyOTP();
    void getOTPDetails();
    void saveOTPDetails();
}
