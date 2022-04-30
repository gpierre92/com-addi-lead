package com.addi.lead.adapter.out.lead;

import com.addi.lead.adapter.out.lead.client.LeadsClient;
import com.addi.lead.adapter.out.lead.exception.NotFoundLeadException;
import com.addi.lead.adapter.out.lead.model.LeadResponse;
import com.addi.lead.domain.port.out.LeadsPort;
import com.addi.lead.entity.Lead;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.Builder;

import java.util.function.Function;

/**
 * Class LeadsApiAdapter
 * Adapter Class for evaluate lead
 *
 * @author gpalacios
 */
@Builder
public class LeadsApiAdapter implements LeadsPort {

    private LeadsClient leadsClient;

    private Function<LeadResponse, Lead> leadConverter;

    /**
     * getLeadById method that allows get info lead by id
     *
     * @param id String
     * @return Single<Lead>
     * @throws NotFoundLeadException when lead not found in our system
     */
    @Override
    public Single<Lead> getLeadById(String id) {
        return Maybe.just(leadsClient.getLeadById(id))
                .map(leadResponse -> leadConverter.apply(leadResponse))
                .switchIfEmpty(Single.error(new NotFoundLeadException(id)))
                .onErrorResumeNext(Single.error(new NotFoundLeadException(id)));
    }
}
