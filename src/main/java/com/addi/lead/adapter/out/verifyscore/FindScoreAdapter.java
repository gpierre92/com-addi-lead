package com.addi.lead.adapter.out.verifyscore;

import com.addi.lead.adapter.out.verifyscore.client.VerifyScoreClient;
import com.addi.lead.domain.port.out.FindScorePort;
import io.reactivex.Single;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * Class FindScoreAdapter
 * Adapter Class for get lead score
 *
 * @author gpalacios
 */
@Slf4j
@Builder
public class FindScoreAdapter implements FindScorePort {

    private VerifyScoreClient verifyScoreClient;

    /**
     * findScoreLeadById method that get score value
     *
     * @param id String
     * @return Single<Integer>
     */
    @Override
    public Single<Integer> findScoreLeadById(String id) {
        return Single.just(verifyScoreClient.verifyById(id))
                .map(score -> {
                    log.info("score: " + score);
                    return score;
                })
                .doOnError(ex -> log.error("Error in verifyScoreLeadById method"));
    }
}
