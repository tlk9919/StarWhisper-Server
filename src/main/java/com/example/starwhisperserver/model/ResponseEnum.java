package com.example.starwhisperserver.model;

public enum ResponseEnum {
    // 通用
    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),

    // 用户相关
    USERNAME_PASSWORD_EMPTY(1001, "用户名或密码不能为空"),
    USER_ALREADY_EXISTS(1002, "用户名已存在"),
    LOGIN_SUCCESS(1003, "登录成功"),
    LOGIN_FAILED(1004, "用户名或密码错误"),
    REGISTER_SUCCESS(1005, "注册成功"),
    REGISTER_FAILED(1006, "注册失败"),

    // 密码相关
    PASSWORD_RESET_SUCCESS(2001, "密码修改成功"),
    PASSWORD_RESET_FAILED(2002, "密码重置失败"),

    // 查询相关
    QUERY_SUCCESS(3001, "查询成功"),
    QUERY_FAILED(3002, "查询失败"),

    // 验证码相关
    VERIFICATION_CODE_SEND_SUCCESS(4001, "验证码发送成功"),
    VERIFICATION_CODE_SEND_FAILED(4002, "验证码发送失败"),
    VERIFICATION_CODE_NOT_FOUND(4003, "验证码不存在"),
    VERIFICATION_CODE_EXPIRED(4004, "验证码已过期"),
    VERIFICATION_CODE_INVALID(4005, "验证码错误"),
    VERIFICATION_CODE_VERIFIED(4006, "验证码验证成功");

    private final int code;
    private final String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
