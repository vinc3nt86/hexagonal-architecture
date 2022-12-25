package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

public interface UseCase<T, P> {
    T execute(P dto);
}
