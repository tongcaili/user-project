package com.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus
    String handleException(Exception e) {
        LOGGER.error("{}", e);
        return "操作失败!";
    }

   /* @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        LOGGER.error("{}", e);
        return "参数错误";
    }*/

    @ExceptionHandler(ParamException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleParamException(ParamException e) {
        LOGGER.error("{}", e);
        return e.getMessage();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleUserNotFoundException(UserNotFoundException e) {
        LOGGER.error("{}", e);
        return e.getMessage();
    }

}
