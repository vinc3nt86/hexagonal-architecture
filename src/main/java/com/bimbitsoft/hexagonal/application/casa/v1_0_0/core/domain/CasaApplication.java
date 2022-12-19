package com.bimbitsoft.hexagonal.application.casa.v1_0_0.core.domain;

import lombok.Builder;
import lombok.Data;

/**
 * Domain Model can be created from two source. The first is in this specific package, and the other method is
 * via POJO generator. The advantage of using POJO generator allow the domain model to be managed by its version.
 */
@Data
@Builder
public class CasaApplication {
    private String applicationId;
    private Status status;

    @Data
    @Builder
    private class Status {
        private int code;
        private String description;
    }
}
