package com.user.vo;

public enum ResponseCode {
    // 自定义的响应码
    SUCCESS(0, "成功"),
    ERROR(1, "失败"),
    no_login(2, "请登录"),
    need_info(10000, "请完善个人信息"),
    ILLEGAL_ARGUMENT(100, "Argument Error"),
    LOGIN_SUCCESS(200, "Login Success"),
    LOGIN_FAILURE(300, "Login Failure"),
    ILLEGAL_TOKEN(400, "Need Authorities"),
    LOGOUT_SUCCESS(500, "Logout Success"),
    NO_PERMISSION(500, "Need Permission"),
    SYSTEM_ERROR(1000, "System Error");

    private final int code;
    private final String desc;


    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}