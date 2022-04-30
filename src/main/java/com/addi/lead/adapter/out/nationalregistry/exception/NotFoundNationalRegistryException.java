package com.addi.lead.adapter.out.nationalregistry.exception;

public class NotFoundNationalRegistryException extends Exception {

    private static final String MESSAGE = "National registry not found in service ";

    public NotFoundNationalRegistryException(String errorCode) {
        super(MESSAGE + errorCode);
    }
}
