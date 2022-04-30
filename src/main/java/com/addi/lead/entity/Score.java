package com.addi.lead.entity;

import com.addi.lead.domain.interactor.exception.VerifyScoreException;
import io.reactivex.Completable;
import lombok.Builder;
import lombok.Getter;

/**
 * Class Score
 *
 * @author gpalacios
 */
@Getter
@Builder
public class Score {

    private static int SCORE_LIMIT = 60;

    private String identityNumber;
    private int score;

    /**
     * evaluate method that allows determined if score is greater than SCORE_LIMIT
     *
     * @throws VerifyScoreException when score is less than SCORE_LIMIT
     */
    public Completable evaluate() {
        if (score <= SCORE_LIMIT) {
            return Completable.error(new VerifyScoreException(identityNumber, score));
        }
        return Completable.complete();
    }

}
