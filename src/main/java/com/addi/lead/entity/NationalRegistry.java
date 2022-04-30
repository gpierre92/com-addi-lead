package com.addi.lead.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NationalRegistry {
    private String firstName;
    private String lastName;
    private String email;
}
