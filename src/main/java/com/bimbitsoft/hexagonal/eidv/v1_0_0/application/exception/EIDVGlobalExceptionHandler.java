package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class EIDVGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EIDVCoreException.class)
    protected ResponseEntity handleException(EIDVCoreException e, Locale locale) {
        //TODO: Handle response based on HttpStatus code define in exception object
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder()
                        .code(e.getErrorCode())
                        .message(e.getErrorMessage())
                        .build()
                );
    }
}
