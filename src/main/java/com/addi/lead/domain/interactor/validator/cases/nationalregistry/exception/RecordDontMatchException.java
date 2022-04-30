package com.addi.lead.domain.interactor.validator.cases.nationalregistry.exception;

public class RecordDontMatchException extends Exception {

    private static final String MESSAGE = "Data in National Registry dont match ";

    public RecordDontMatchException(String errorCode) {
        super(MESSAGE + errorCode);
    }
}
