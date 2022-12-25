package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model;

import lombok.Data;

@Data
public class EIDVDocument {
    public enum DocumentType {
        ID_DOCUMENT,
        LIVE_VIDEO
    }
}
