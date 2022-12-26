package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.IdDocument;

public interface FileManagerPort {
    String generateFilename(String channelApplicationId, String applicantId, IdDocument idDocument);

    boolean scanForVirus(String resourceFilePath);

    String upload(String resourceFilePath);
    
    void deleteResourceFile(String resourceFilePath);

    String createResourceFilePath(byte[] binaryData);
}
