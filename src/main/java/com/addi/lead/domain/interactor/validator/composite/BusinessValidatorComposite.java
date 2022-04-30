package com.addi.lead.domain.interactor.validator.composite;

import io.reactivex.Completable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class BusinessValidatorComposite
 * Class add new rules and validate in parallel
 *
 * @author gpalacios
 */
public class BusinessValidatorComposite<T> implements BusinessValidator<T> {

    private List<BusinessValidator<T>> validations;

    public BusinessValidatorComposite() {
        validations = new ArrayList<>();
    }

    /**
     * addValidator
     * method to add new rules
     *
     * @param validator {@link BusinessValidator}
     */
    public void addValidator(final BusinessValidator<T> validator) {
        if (validator != null) {
            validations.add(validator);
        }
    }

    /**
     * validate method to validate rules in parallel
     *
     * @param data T
     */
    @Override
    public Completable validate(T data) {
        List<Completable> iterable = validations
                .stream()
                .map(v -> v.validate(data))
                .collect(Collectors.toList());
        return Completable.concat(iterable);
    }
}
