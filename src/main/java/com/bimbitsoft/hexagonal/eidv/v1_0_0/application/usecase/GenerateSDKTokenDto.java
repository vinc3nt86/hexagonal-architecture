package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
class GenerateSDKTokenDto {
    private String applicantId;
}
