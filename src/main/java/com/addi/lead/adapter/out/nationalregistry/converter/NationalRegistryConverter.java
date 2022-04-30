package com.addi.lead.adapter.out.nationalregistry.converter;

import com.addi.lead.adapter.out.nationalregistry.model.NationalRegistryResponse;
import com.addi.lead.entity.NationalRegistry;
import lombok.Builder;

import java.util.function.Function;

@Builder
public class NationalRegistryConverter implements Function<NationalRegistryResponse, NationalRegistry> {
    @Override
    public NationalRegistry apply(NationalRegistryResponse nationalRegistryResponse) {
        return NationalRegistry.builder()
                .firstName(nationalRegistryResponse.getFirstName())
                .lastName(nationalRegistryResponse.getLastName())
                .email(nationalRegistryResponse.getEmail())
                .build();
    }
}
