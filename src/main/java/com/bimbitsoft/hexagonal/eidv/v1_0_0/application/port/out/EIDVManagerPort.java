package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.IdDocument;

public interface EIDVManagerPort {
    String createApplicant(String firstName, String lastName);

    String generateSDKToken(String applicantId);

    byte[] downloadDocumentBinaryData(String documentUid);

    IdDocument retrieveDocumentById(String documentUid);
}
