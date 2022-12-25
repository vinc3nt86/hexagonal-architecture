package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class EIDVCoreException extends RuntimeException {
    private HttpStatus httpStatus;
    private String errorCode;
    private String errorMessage;

    public EIDVCoreException(String message) {
        super(message);
    }

    public EIDVCoreException(ExceptionCode exception) {
        this.errorCode = exception.getCode();
        this.errorMessage = exception.getMessage();
        this.httpStatus = exception.getHttpStatus();
    }

    public enum ExceptionCode {
        LIVE_VIDEO_FRAME_EXTRACTION_FAILED("Failed to extract live video frame", "", HttpStatus.EXPECTATION_FAILED),
        LIVE_VIDEO_FRAME_EXTRACTION_UNAVAILABLE("", "", HttpStatus.INTERNAL_SERVER_ERROR);

        @Getter
        private String code;
        @Getter
        private String message;
        @Getter
        private HttpStatus httpStatus;

        ExceptionCode(String message, String code, HttpStatus httpStatus) {
            this.code = code;
            this.message = message;
            this.httpStatus = httpStatus;
        }

    }
}
