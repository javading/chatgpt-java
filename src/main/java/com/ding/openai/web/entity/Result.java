package com.ding.openai.web.entity;

import com.ding.openai.exception.BaseException;
import com.ding.openai.exception.ErrorType;
import com.ding.openai.exception.ErrorTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
public class Result<T> {
    public static final String SUCCESSFUL_CODE = "000000";
    public static final String SUCCESSFUL_MSG = "处理成功";

    private String code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    /**
     * 异常类型
     */
    @JsonIgnore
    private transient ErrorType errorType;

    public Result() {
    }

    /**
     * @param errorType
     */
    public Result(ErrorType errorType) {
        this.errorType = errorType;
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
    }

    /**
     * @param errorType
     * @param data
     */
    public Result(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param msg
     * @param data
     */
    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result success(Object data) {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MSG, data);
    }

    public static Result success(String msg, Object data) {
        return new Result<>(SUCCESSFUL_CODE, msg, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static Result fail() {
        return new Result(ErrorTypeEnum.SYS_ERROR);
    }

    public static Result fail(String msg, Object data) {
        return new Result<>(ErrorTypeEnum.SYS_ERROR.getCode(), msg, data);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @param baseException
     * @return Result
     */
    public static Result fail(BaseException baseException) {
        return fail(baseException, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(BaseException baseException, Object data) {
        return new Result<>(baseException.getCode(), baseException.getMsg(), data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @param data
     * @return Result
     */
    public static Result fail(ErrorType errorType, Object data) {
        return new Result<>(errorType, data);
    }

    public static Result fail(ErrorType errorType, String errorMsg, Object data) {
        return new Result<>(errorType.getCode(), errorMsg, data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @return Result
     */
    public static Result fail(ErrorType errorType) {
        return Result.fail(errorType, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(Object data) {
        return new Result<>(ErrorTypeEnum.SYS_ERROR, data);
    }


    /**
     * 成功code=000000
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }
}
