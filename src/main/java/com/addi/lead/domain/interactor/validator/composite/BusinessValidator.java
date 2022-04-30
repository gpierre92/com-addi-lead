package com.addi.lead.domain.interactor.validator.composite;

import io.reactivex.Completable;

/**
 * Class BusinessValidator
 * validate business cases interface
 *
 * @author gpalacios
 */
public interface BusinessValidator<T> {

    /**
     * Validate business cases method
     *
     * @param data T
     */
    Completable validate(T data);
}
