package com.example.starwhisperserver.model;

import lombok.Data;

/**
 * @author admin
 * @classnam RegisterRequest
 * @time 22:25
 * @date 2024/12/12
 */
@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String code;
}
