package com.addi.lead.domain.port.out;

import com.addi.lead.entity.NationalRegistry;
import io.reactivex.Single;

public interface NationalRegistryPort {
    Single<NationalRegistry> verifyNationalRegistryById(String id);
}
