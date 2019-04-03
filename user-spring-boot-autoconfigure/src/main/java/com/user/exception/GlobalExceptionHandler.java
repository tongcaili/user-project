package com.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity<String> handleException(Exception e){
        LOGGER.error("{}",e);
        return ResponseEntity.status(600).body("操作失败");
    }

   /* @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        LOGGER.error("{}",e);
        return ResponseEntity.badRequest().body("参数错误");
    }*/

    @ExceptionHandler(ParamException.class)
    @ResponseBody
    ResponseEntity<String> handleParamException(ParamException e){
        LOGGER.error("{}",e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e){
        LOGGER.error("{}",e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
