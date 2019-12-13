package com.fintech.modules.boot.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * Description
 * <br>   自定义Drools异常类
 *
 * @author chendongdong
 * @date 2019/12/10
 **/
@Data
public class DroolsException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 5449355956170645749L;


    public DroolsException() {
    }

    public DroolsException(String message) {
        super(message);
    }

    public DroolsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DroolsException(Throwable cause) {
        super(cause);
    }

    public DroolsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
