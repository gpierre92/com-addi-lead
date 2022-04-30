package com.addi.lead.domain.port.out;

import com.addi.lead.entity.Lead;
import io.reactivex.Single;

public interface LeadsPort {
    Single<Lead> getLeadById(String id);
}
