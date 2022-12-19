package com.bimbitsoft.hexagonal.application.casa.v1_0_0.core.port.out;

public interface CasaProduct {
    void generateAccountNumber();
    void createAccount();
    void freezeAccount();
    void waiveAccountFee();
}
