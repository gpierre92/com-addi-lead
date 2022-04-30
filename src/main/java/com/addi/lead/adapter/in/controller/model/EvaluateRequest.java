package com.addi.lead.adapter.in.controller.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Validated
public class EvaluateRequest {
    @NotNull
    private String identityNumber;
}

