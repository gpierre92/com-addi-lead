package com.addi.lead.adapter.out.lead.exception;

public class NotFoundLeadException extends Exception {

    private static final String MESSAGE = "Lead not found in service ";

    public NotFoundLeadException(String errorCode) {
        super(MESSAGE + errorCode);
    }
}
