package com.addi.lead.adapter.out.lead.converter;

import com.addi.lead.adapter.out.lead.model.LeadResponse;
import com.addi.lead.entity.Lead;
import lombok.Builder;

import java.util.function.Function;

@Builder
public class LeadConverter implements Function<LeadResponse, Lead> {
    @Override
    public Lead apply(LeadResponse leadResponse) {
        return Lead.builder()
                .identityNumber(leadResponse.getIdentityNumber())
                .firstName(leadResponse.getFirstName())
                .lastName(leadResponse.getLastName())
                .email(leadResponse.getEmail())
                .build();
    }
}
