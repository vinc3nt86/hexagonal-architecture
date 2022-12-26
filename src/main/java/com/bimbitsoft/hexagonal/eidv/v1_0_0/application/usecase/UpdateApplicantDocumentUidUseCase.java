package com.bimbitsoft.hexagonal.eidv.v1_0_0.application.usecase;

import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.exception.EIDVCoreException;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.model.IdDocument;
import com.bimbitsoft.hexagonal.eidv.v1_0_0.application.port.out.EIDVApplicantRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
class UpdateApplicantDocumentUidUseCase implements UseCase<String, UpdateApplicantDocumentUidUseCase.Dto> {
    private final EIDVApplicantRepositoryPort eidvApplicantRepositoryPort;

    @Override
    public String execute(UpdateApplicantDocumentUidUseCase.Dto dto) {
        log.info("Executing {} use-case", this.getClass().getName());

        String applicantId = eidvApplicantRepositoryPort.findApplicantIdByChannelApplicationId(dto.getChannelApplicationId())
                .orElseThrow(() -> {
                    log.error("An attempt was made to update {} id for applicant which is not found in application id {}",
                            dto.getDocumentType().name(),
                            dto.getChannelApplicationId());
                    throw new EIDVCoreException(EIDVCoreException.ExceptionCode.APPLICANT_ID_NOT_FOUND);
                });

        switch (dto.documentType) {
            case ID_DOCUMENT:
                eidvApplicantRepositoryPort.updateIdDocumentIdByApplicantId(dto.getDocumentUid(), applicantId);
                break;
            case LIVE_VIDEO:
                eidvApplicantRepositoryPort.updateLiveVideoIdByApplicantId(dto.getDocumentUid(), applicantId);
                break;
        }

        return applicantId;
    }

    @Getter
    @Builder
    static class Dto {
        private String channelApplicationId;
        private String documentUid;
        private IdDocument.DocumentType documentType;
    }
}
