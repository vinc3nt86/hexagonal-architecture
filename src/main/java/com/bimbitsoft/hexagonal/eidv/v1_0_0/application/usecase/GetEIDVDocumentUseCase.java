package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.IdDocument;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out.EIDVManagerPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
class GetEIDVDocumentUseCase implements UseCase<IdDocument, String> {
    private final EIDVManagerPort eidvManagerPort;

    @Override
    public IdDocument execute(String documentUid) {
        log.info("Executing {} use-case", this.getClass().getName());

        return eidvManagerPort.retrieveDocumentById(documentUid);
    }
}
