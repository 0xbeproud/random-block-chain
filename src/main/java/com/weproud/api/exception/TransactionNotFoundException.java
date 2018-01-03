package com.weproud.api.exception;

/**
 * @author Logan. 81k
 */
public class TransactionNotFoundException extends ServiceException {

    public TransactionNotFoundException() {
        super("Transaction을 찾을 수 없습니다.");
    }
}
