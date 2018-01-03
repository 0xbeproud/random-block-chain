package com.weproud.api.exception;

import java.lang.annotation.Annotation;

public abstract class Ensure {
    private Ensure() {
    }

    public static <T> T notNull(T reference) {
        if (reference == null)
            throw new IllegalArgumentException();
        return reference;
    }

    public static <T> T notNull(T reference, String parameterName) {
        if (reference == null)
            throw new IllegalArgumentException(parameterName + " cannot be null");
        return reference;
    }

    public static void isTrue(boolean expression) {
        if (!expression)
            throw new IllegalArgumentException();
    }

    public static <T> T notAnnotated(T reference, Class<? extends Annotation> annotaion) {
        if (reference == null)
            throw new IllegalArgumentException("No Method annotated with " + annotaion.getSimpleName());
        return reference;
    }
}