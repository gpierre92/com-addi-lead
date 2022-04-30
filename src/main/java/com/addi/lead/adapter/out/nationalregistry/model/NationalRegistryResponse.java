package com.addi.lead.adapter.out.nationalregistry.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NationalRegistryResponse {
    private String firstName;
    private String lastName;
    private String email;
}
