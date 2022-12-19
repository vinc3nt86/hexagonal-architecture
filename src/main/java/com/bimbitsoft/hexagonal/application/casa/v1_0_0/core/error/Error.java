package com.bimbitsoft.hexagonal.application.casa.v1_0_0.core.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Immutable;

@AllArgsConstructor
@Getter
@Immutable
public class Error {
    private String code;
    private String message;
}
