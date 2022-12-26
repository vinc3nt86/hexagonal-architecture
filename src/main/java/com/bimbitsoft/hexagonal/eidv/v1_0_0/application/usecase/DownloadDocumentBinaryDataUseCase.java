package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out.EIDVManagerPort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@AllArgsConstructor
@Slf4j
class DownloadDocumentBinaryDataUseCase implements UseCase<String, DownloadDocumentBinaryDataUseCase.Dto> {
    private final EIDVManagerPort eidvManagerPort;

    @Override
    public String execute(Dto dto) {
        log.info("Executing {} use-case", this.getClass().getName());

        byte[] binaryData = eidvManagerPort.downloadDocumentBinaryData(dto.getDocumentUid());
        return Base64.getEncoder().encodeToString(binaryData);
    }

    @Getter
    @Builder
    static class Dto {
        private final String documentUid;
    }
}
