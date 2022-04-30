package com.addi.lead.domain.port.in;

import com.addi.lead.entity.Lead;
import io.reactivex.Completable;

public interface LeadInteractor {
    Completable doEvaluate(String identityNumber);
}
