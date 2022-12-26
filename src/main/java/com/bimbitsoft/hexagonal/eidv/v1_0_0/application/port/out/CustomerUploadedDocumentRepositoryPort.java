package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.IdDocument;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.UploadedDocumentRecord;

import java.util.Optional;

public interface CustomerUploadedDocumentRepositoryPort {
    Optional<UploadedDocumentRecord> findByApplicationIdAndDocumentType(String channelApplicationId, IdDocument.DocumentType type);

    void add(UploadedDocumentRecord uploadedDocumentRecord, String applicationId);

    void update(UploadedDocumentRecord uploadedDocumentRecord, String applicationId);
}
