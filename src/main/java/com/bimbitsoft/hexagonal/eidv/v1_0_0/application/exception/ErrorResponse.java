package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String code;
    private String message;
}
