package com.addi.lead.domain.interactor.validator.cases.judicialrecord;

import com.addi.lead.domain.interactor.validator.composite.BusinessValidator;
import com.addi.lead.domain.port.out.JudicialRecordPort;
import com.addi.lead.entity.Lead;
import io.reactivex.Completable;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * Class JudicialRecordValidator
 * validate judicial record by identity number
 *
 * @author gpalacios
 */
@Slf4j
@Builder
public class JudicialRecordValidator implements BusinessValidator<Lead> {

    private JudicialRecordPort judicialRecordPort;

    @Override
    public Completable validate(Lead lead) {
        log.info("Starting JudicialRecordValidator.validate() method");
        return judicialRecordPort.verifyJudicialRecordById(lead.getIdentityNumber())
                .doOnError(ex -> log.info("Finished JudicialRecordValidator.validate() method with error"))
                .doOnComplete(() -> log.info("Finished JudicialRecordValidator.validate() method"));
    }
}
