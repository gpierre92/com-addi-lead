package com.addi.lead.adapter.out.lead.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LeadResponse {
    private String identityNumber;
    private String firstName;
    private String lastName;
    private String email;
    private int status;
}
