package com.addi.lead.domain.port.out;

import io.reactivex.Completable;

public interface JudicialRecordPort {
    Completable verifyJudicialRecordById(String id);
}
