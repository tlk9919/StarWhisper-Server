package com.example.starwhisperserver.service;

import com.example.starwhisperserver.model.VerificationCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin_tlk
 * @since 2024-12-12
 */
public interface IVerificationCodeService extends IService<VerificationCode> {
    //发送验证码
    void sendVerificationCode(String email);
    //验证验证码
    boolean verifyCode(String email, String code);

}
