package com.addi.lead.domain.port.out;

import io.reactivex.Single;

public interface FindScorePort {
    Single<Integer> findScoreLeadById(String id);
}
