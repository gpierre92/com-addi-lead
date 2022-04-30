package com.addi.lead.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Lead {
    private String identityNumber;
    private String firstName;
    private String lastName;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lead that = (Lead) o;
        return firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) && email.equals(that.email);
    }
}

