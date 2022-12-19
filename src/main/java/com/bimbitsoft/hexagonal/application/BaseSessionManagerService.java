package com.bimbitsoft.hexagonal.application;

public interface BaseSessionManagerService {
    void createSession();
    void validateSameSession();
}
