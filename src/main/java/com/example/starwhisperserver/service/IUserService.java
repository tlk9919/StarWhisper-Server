package com.example.starwhisperserver.service;

import com.example.starwhisperserver.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin_tlk
 * @since 2024-12-12
 */
public interface IUserService extends IService<User> {
    //注册re
    boolean register(String username ,String password,String code);
    //登录
    String login(String username ,String password);
    //忘记密码
    boolean resetPassword(String email, String code, String newPassword,String confirmPassword);
    //查询所有用户
    List<User> getAllUsers();
    //根据用户名查询
    User getUserByUsername(String username);
    //删除用户
    void deleteUser(String username );
}
