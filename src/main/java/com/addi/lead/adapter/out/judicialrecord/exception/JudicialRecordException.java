package com.addi.lead.adapter.out.judicialrecord.exception;

public class JudicialRecordException extends Exception {

    private static final String MESSAGE = "Error in Judicial Record ";

    public JudicialRecordException(String errorCode) {
        super(MESSAGE + errorCode);
    }
}
