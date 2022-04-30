package com.addi.lead.domain.interactor.exception;

public class VerifyScoreException extends Exception {

    private static final String MESSAGE = "Score is not greater than 60, actual: %s for id: %s";

    public VerifyScoreException(String id, int score) {
        super(String.format(MESSAGE, score, id));
    }
}
