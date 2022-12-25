package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out;

public interface EIDVManagerPort {
    String createApplicant(String firstName, String lastName);

    String generateSDKToken(String applicantId);

    byte[] downloadDocumentBinaryData(String documentId);
}
