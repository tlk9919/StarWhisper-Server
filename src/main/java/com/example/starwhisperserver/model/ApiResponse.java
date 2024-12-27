package com.example.starwhisperserver.model;

import com.example.starwhisperserver.model.ResponseEnum;
import lombok.Data;

/**
 * @author admin
 * @classnam ApiResponse
 * @time 22:26
 * @date 2024/12/12
 */
//统一返回格式
@Data
public class ApiResponse<T> {
    private Integer code;
    private String message;
    private String token;
    private T data;

    public ApiResponse(ResponseEnum responseEnum) {
        this.code=responseEnum.getCode();
        this.message=responseEnum.getMessage();
    }
    public ApiResponse(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
    }
}
