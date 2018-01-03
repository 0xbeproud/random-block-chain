package com.weproud.api.exception;

/**
 * @author Logan. 81k
 */
public class BlockNotFoundException extends ServiceException {

    public BlockNotFoundException() {
        super("Block을 찾을 수 없습니다.");
    }
}
