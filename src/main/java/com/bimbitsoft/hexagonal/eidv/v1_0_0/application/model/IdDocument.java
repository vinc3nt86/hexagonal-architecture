package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model;

import lombok.Data;

import java.util.Base64;

@Data
public class IdDocument {
    private String uid;
    private String filename;
    private String fileType;
    private DocumentType type;
    private String issuingCountry;
    private byte[] binaryData;
    private Integer fileSize;
    private String fileSide;
    private String applicantId;

    public static String encode(byte[] binaryData) {
        return Base64.getEncoder().encodeToString(binaryData);
    }

    public static byte[] decode(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }

    public boolean isFileSizeExceed(int limit) {
        return fileSize > limit;
    }

    public String getFileExtension() {
        if (filename.contains("\\.")) {
            String[] substring = filename.split("\\.");
            return substring[substring.length - 1];
        }
        return "";
    }

    public enum DocumentType {
        ID_DOCUMENT,
        LIVE_VIDEO
    }
}
