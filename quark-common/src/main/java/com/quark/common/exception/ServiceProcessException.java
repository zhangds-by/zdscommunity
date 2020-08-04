package com.quark.common.exception;

/**
 * 
 * @author zhangds
 * @date 2020/7/31 16:06
 */
public class ServiceProcessException extends RuntimeException {

    public ServiceProcessException(String message) {
        super(message);
    }

    public ServiceProcessException(String message, Throwable cause) {
        super(message, cause);
    }


}
