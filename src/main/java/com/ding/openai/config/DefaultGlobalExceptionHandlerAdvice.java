package com.ding.openai.config;

import com.ding.openai.exception.BaseException;
import com.ding.openai.exception.ErrorTypeEnum;
import com.ding.openai.web.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

@Slf4j
@RestControllerAdvice
public class DefaultGlobalExceptionHandlerAdvice {
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public Result missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("missing servlet request parameter com.ding.openai.exception:{}", ex.getMessage());
      
        return Result.fail(ErrorTypeEnum.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {MultipartException.class})
    public Result uploadFileLimitException(MultipartException ex) {
        log.error("upload file size limit:{}", ex.getMessage());
        return Result.fail(ErrorTypeEnum.UPLOAD_FILE_SIZE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result serviceException(MethodArgumentNotValidException ex) {
        log.error("service com.ding.openai.exception:{}", ex.getMessage());
        return Result
                .fail(ErrorTypeEnum.ARGUMENT_NOT_VALID, ex.getBindingResult().getFieldError().getDefaultMessage(),
                      null);
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public Result duplicateKeyException(DuplicateKeyException ex) {
        log.error("primary key duplication com.ding.openai.exception:{}", ex.getMessage());
        return Result.fail(ErrorTypeEnum.DUPLICATE_PRIMARY_KEY);
    }

    @ExceptionHandler(value = {BaseException.class})
    public Result baseException(BaseException ex) {
        log.error("base com.ding.openai.exception:{}", ex.getMsg());
        return Result.fail(ex);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(Exception ex) {
        log.error("INTERNAL_SERVER_ERROR: " + ex.getMessage(), ex);
        return Result.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result throwable(Throwable ex) {
        log.error("INTERNAL_SERVER_ERROR: " + ex.getMessage(), ex);
        return Result.fail();
    }

    //IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("参数错误", e);
        return Result.fail(ErrorTypeEnum.INVALID_PARAM, e.getMessage(), null);
    }
}
