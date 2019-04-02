package com.user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String message;
    private T data;

    private R(int code) {
        this.code = code;
    }

    private R(int code, T data) {
        this.code = code;
        this.data = data;
    }

    private R(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private R(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> R<T> ok() {
        return new R<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> R<T> okMsg(String msg) {
        return new R<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> ok(T data) {
        return new R<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> R<T> okMsg(Integer code, T data, String msg) {
        return new R<T>(code, msg, data);
    }

    public static <T> R<T> ok(String msg, T data) {
        return new R<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> R<T> errMsg() {
        return new R<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static <T> R<T> errMsg(T data, String msg) {
        return new R<T>(ResponseCode.ERROR.getCode(), msg, data);
    }

    public static <T> R<T> errMsg(Integer code, T data, String msg) {
        return new R<T>(code, msg, data);
    }

    public static <T> R<T> errMsg(String msg) {
        return new R<T>(ResponseCode.ERROR.getCode(), msg);
    }

    public boolean isOk() {
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

}