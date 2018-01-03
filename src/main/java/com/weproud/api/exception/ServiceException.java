package com.weproud.api.exception;

import lombok.Getter;

/**
 * @author Logan.k
 */
public class ServiceException extends RuntimeException {
    @Getter
    protected String code;

    public ServiceException(final String message) {
        super(message);
        this.code = this.getClass().getSimpleName();
    }

    public ServiceException(final String code, final String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(final Throwable throwable) {
        super(throwable);
    }
}
