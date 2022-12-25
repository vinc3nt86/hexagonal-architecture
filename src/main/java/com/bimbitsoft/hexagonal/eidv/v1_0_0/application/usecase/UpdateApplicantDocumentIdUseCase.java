package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.domain.EIDVApplicant;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.domain.EIDVDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
class UpdateApplicantDocumentIdUseCase implements UseCase<EIDVApplicant, UpdateApplicantDocumentIdUseCase.Dto>{

    @Override
    public EIDVApplicant execute(UpdateApplicantDocumentIdUseCase.Dto dto) {
        log.info("Executing {} use-case", this.getClass().getName());

        return null;
    }

    @Getter
    @Builder
    static class Dto {
        private String channelApplicationId;
        private String documentId;
        private EIDVDocument.DocumentType documentType;
    }
}
