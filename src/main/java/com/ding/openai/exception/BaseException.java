package com.ding.openai.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {

    private String msg;
    private String code;

    public BaseException(ErrorTypeEnum error) {
        super(error.getMsg());
        this.code = error.getCode();
        this.msg = error.getMsg();
    }

    public BaseException(String msg) {
        super(msg);
        this.code = ErrorTypeEnum.SYS_ERROR.getCode();
        this.msg = msg;
    }

    public BaseException() {
        super(ErrorTypeEnum.SYS_ERROR.getMsg());
        this.code = ErrorTypeEnum.SYS_ERROR.getCode();
        this.msg = ErrorTypeEnum.SYS_ERROR.getMsg();
    }
}
