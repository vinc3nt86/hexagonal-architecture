package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out;

public interface EIDVAuditRepository {
    void addApplicantStatusRecord(String applicantId, String toString);
}
