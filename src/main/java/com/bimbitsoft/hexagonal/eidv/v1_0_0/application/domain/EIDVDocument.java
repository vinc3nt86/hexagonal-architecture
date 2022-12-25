package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.domain;

import lombok.Data;

@Data
public class EIDVDocument {
    public enum DocumentType {
        ID_DOCUMENT,
        SELFIE_VIDEO_FRAME
    }
}
