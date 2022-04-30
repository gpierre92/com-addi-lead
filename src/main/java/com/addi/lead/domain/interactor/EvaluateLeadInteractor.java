package com.addi.lead.domain.interactor;

import com.addi.lead.aplication.util.ValidateUtil;
import com.addi.lead.domain.interactor.validator.composite.BusinessValidator;
import com.addi.lead.domain.port.in.LeadInteractor;
import com.addi.lead.domain.port.out.FindScorePort;
import com.addi.lead.domain.port.out.LeadsPort;
import com.addi.lead.domain.port.out.ProduceEventPort;
import com.addi.lead.entity.Lead;
import com.addi.lead.entity.Score;
import io.reactivex.Completable;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * Class EvaluateLeadInteractor
 * Interactor Class for evaluate differents rules
 *
 * @author gpalacios
 */
@Slf4j
@Builder
public class EvaluateLeadInteractor implements LeadInteractor {
    private BusinessValidator<Lead> businessValidator;
    private LeadsPort leadsPort;
    private FindScorePort findScorePort;

    private ProduceEventPort produceEventPort;

    @Override
    public Completable doEvaluate(String identityNumber) {

        ValidateUtil.isValidate(this.getClass(), identityNumber);
        return leadsPort.getLeadById(identityNumber)
                .flatMapCompletable(lead -> businessValidator.validate(lead)
                .andThen(this.evaluateScore(identityNumber))
                //TODO Is all Ok here? Now send event to Topic for next steps
                .andThen(produceEventPort.produce(lead)))
                .doOnComplete(() -> log.info("Finished doEvaluate method, Lead will convert to Prospect now"))
                .doOnError(ex -> log.error("Finished doEvaluate method with error " + ex));
    }

    /**
     * evaluateScore method that allows build score and evaluate lead by identityNumber
     *
     * @param identityNumber String
     */
    private Completable evaluateScore(String identityNumber) {
        return findScorePort.findScoreLeadById(identityNumber)
                .flatMapCompletable(scoreValue -> {

                    Score score = Score.builder()
                            .identityNumber(identityNumber)
                            .score(scoreValue)
                            .build();

                    return score.evaluate();
                });

    }
}
