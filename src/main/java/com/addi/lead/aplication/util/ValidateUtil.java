package com.addi.lead.aplication.util;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public final class ValidateUtil {
    private ValidateUtil() {

    }

    public static void isNullValidate(Class clazz, String field) {
        if (Objects.isNull(field)) {
            log.error("Error in isNullValidate");
            throw new IllegalArgumentException("Error in isNullValidate method: parameter in class: " + clazz);
        }
    }

    public static void isEmptyValidate(Class clazz, String field) {
        if (field.isEmpty()) {
            log.error("Error in isEmptyValidate");
            throw new IllegalArgumentException("Error in isEmptyValidate method: parameter in class: " + clazz);
        }
    }

    public static void isValidate(Class clazz, String field) {
        if (Strings.isNullOrEmpty(field)) {
            log.error("Error in isValidate");
            throw new IllegalArgumentException("Error in isEmptyValidate method: parameter in class: " + clazz);
        }
    }
}
