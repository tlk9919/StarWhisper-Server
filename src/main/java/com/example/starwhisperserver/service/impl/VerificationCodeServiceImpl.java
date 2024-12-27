package com.example.starwhisperserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.starwhisperserver.model.VerificationCode;
import com.example.starwhisperserver.dao.VerificationCodeMapper;
import com.example.starwhisperserver.service.IVerificationCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin_tlk
 * @since 2024-12-12
 */
@Service
public class VerificationCodeServiceImpl extends ServiceImpl<VerificationCodeMapper, VerificationCode> implements IVerificationCodeService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async
    public void sendVerificationCode(String email){
        // 生成6位随机验证码
        String code=String.format("%06d",new Random().nextInt(999999));
        //设置5分钟后过期
        Date expires=new Date(System.currentTimeMillis()+5*60*1000);
        // 查询邮箱是否已经有验证码记录
        QueryWrapper<VerificationCode> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("email",email);
        VerificationCode existingVerificationCode=this.getOne(queryWrapper);
        // 如果验证码记录存在，更新验证码信息
        if(existingVerificationCode!=null){
            existingVerificationCode.setCode(code);
            existingVerificationCode.setExpires(expires);
            this.updateById(existingVerificationCode);
        }
        // 如果验证码记录不存在，保存新的验证码
        else {
            VerificationCode verificationCode=new VerificationCode();
            verificationCode.setEmail(email);
            verificationCode.setCode(code);
            verificationCode.setExpires(expires);
            this.save(verificationCode);
        }
        try {
            //发送邮件
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom("tlk_sure@126.com");
            message.setTo(email);
            message.setSubject("星语小屋");
            message.setText("【星语小屋】您的验证码是: " + code + "，验证码5分钟内有效。");
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean verifyCode(String email, String code){
        // 1. 查询验证码记录
        QueryWrapper<VerificationCode> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("code",code);
        // 2. 验证
        VerificationCode verificationCode=this.getOne(queryWrapper);
        if(verificationCode==null){
            return false;
        }
        // 验证码过期，删除记录
        if(new Date().after(verificationCode.getExpires())){
            this.removeById(verificationCode.getId());
            return false;
        }
        // 3. 验证验证码是否匹配
        boolean result=code.equals(verificationCode.getCode());
        return result;
    }
}
