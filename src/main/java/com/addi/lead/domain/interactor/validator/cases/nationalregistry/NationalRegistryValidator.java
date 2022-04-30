package com.addi.lead.domain.interactor.validator.cases.nationalregistry;

import com.addi.lead.domain.interactor.validator.cases.nationalregistry.exception.RecordDontMatchException;
import com.addi.lead.domain.interactor.validator.composite.BusinessValidator;
import com.addi.lead.domain.port.out.NationalRegistryPort;
import com.addi.lead.entity.Lead;
import io.reactivex.Completable;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * Class NationalRegistryValidator
 * validate information national registry by identity number
 *
 * @author gpalacios
 */
@Slf4j
@Builder
public class NationalRegistryValidator implements BusinessValidator<Lead> {

    private NationalRegistryPort nationalRegistryPort;

    @Override
    public Completable validate(Lead lead) {
        log.info("Starting NationalRegistryValidator method");
        return nationalRegistryPort.verifyNationalRegistryById(lead.getIdentityNumber())
                .flatMapCompletable(nationalRegistry -> {

                    Lead leadToCompare = Lead.builder()
                            .firstName(nationalRegistry.getFirstName())
                            .lastName(nationalRegistry.getLastName())
                            .email(nationalRegistry.getEmail())
                            .build();

                    if (!lead.equals(leadToCompare)) {
                        log.error("Data dont match");
                        return Completable.error(new RecordDontMatchException(lead.getIdentityNumber()));
                    }
                    return Completable.complete();
                })
                .doOnError(ex -> log.info("Finished NationalRegistryValidator.validate() method with error"))
                .doOnComplete(() -> log.info("Finished NationalRegistryValidator method"));
    }
}
