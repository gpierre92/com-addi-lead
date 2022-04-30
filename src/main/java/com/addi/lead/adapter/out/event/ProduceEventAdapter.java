package com.addi.lead.adapter.out.event;

import com.addi.lead.domain.port.out.ProduceEventPort;
import com.addi.lead.entity.Lead;
import io.reactivex.Completable;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
public class ProduceEventAdapter implements ProduceEventPort {

    /**
     * produce evetn to topic for next steps
     *
     * @param lead Lead
     * @return Completable
     */
    @Override
    public Completable produce(Lead lead) {
        //TODO for purpose test return this
        return Completable.complete();
    }
}
