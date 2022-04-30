package com.addi.lead.domain.port.out;

import com.addi.lead.entity.Lead;
import io.reactivex.Completable;

public interface ProduceEventPort {

    Completable produce(Lead lead);
}
