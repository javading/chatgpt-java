package com.ding.openai.exception;

public interface ErrorType {

    /**
     * 返回code
     *
     * @return
     */
    String getCode();

    /**
     * 返回mesg
     *
     * @return
     */
    String getMsg();
}
