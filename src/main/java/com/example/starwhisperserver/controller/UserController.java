package com.example.starwhisperserver.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.starwhisperserver.model.ApiResponse;
import com.example.starwhisperserver.model.RegisterRequest;
import com.example.starwhisperserver.model.ResetPasswordRequest;
import com.example.starwhisperserver.model.User;
import com.example.starwhisperserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.starwhisperserver.model.ResponseEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin_tlk
 * @since 2024-12-12
 */
@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    //注册
    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody RegisterRequest request){
        Map<String,String> response=new HashMap<>();
        //检查非空
        if(request.getUsername().isEmpty()||request.getPassword().isEmpty()){
            return new ApiResponse<>(ResponseEnum.USERNAME_PASSWORD_EMPTY);
        }
        //调用服务层处理数据
        boolean res=userService.register(request.getUsername(),request.getPassword(),request.getCode());
        //成功
        if(res){
            return new ApiResponse<>(ResponseEnum.REGISTER_SUCCESS);
        }
        return new ApiResponse<>(ResponseEnum.USER_ALREADY_EXISTS);
    }
    //登录
    @PostMapping("/login")
    public  ApiResponse<String>  login(@RequestBody User user){
        //检查非空
        String username=user.getUsername();
        String password=user.getPassword();
        if(username==null||password==null){
            return new ApiResponse<>(ResponseEnum.USERNAME_PASSWORD_EMPTY);
        }
        //调用服务处理数据
        String token= userService.login(username,password);

        //成功
        if (token != null) {
            return new ApiResponse<>(ResponseEnum.LOGIN_SUCCESS,token);
        }
        return new ApiResponse<>(ResponseEnum.LOGIN_FAILED);
    }
    //重置密码
    @PutMapping("/reset-password")
    public  ApiResponse<String>  resetPassword(@RequestBody ResetPasswordRequest request){//RegisterRequest 含有相应字段
        Map<String,String> response=new HashMap<>();
        //非空校验
        System.out.println("接收到的重置密码请求: " + request);

        if(request.getEmail()==null||request.getNewPassword() ==null) {
            return new ApiResponse<>(ResponseEnum.LOGIN_FAILED);
        }
        String username=request.getEmail();
        String newPassword=request.getNewPassword();
        String code=request.getCode();
        String confirmPassword=request.getConfirmPassword();
        //调用服务
       boolean result= userService.resetPassword(username,newPassword,confirmPassword,code);
       System.out.println("控制器result："+result);
        if(result){
            return new ApiResponse<>(ResponseEnum.PASSWORD_RESET_SUCCESS);
        }
        return new ApiResponse<>(ResponseEnum.PASSWORD_RESET_FAILED);
    }
    //查询所有用户
    @GetMapping("/users")
    public ApiResponse<String> getAllUsers(){
        //调用服务处层
        List<User> user = userService.getAllUsers();
        if(user!=null){
            System.out.println("查询成功"+user);
            return new ApiResponse<>(ResponseEnum.SUCCESS);
        }
       else {
            System.out.println("查询失败");
            return new ApiResponse<>(ResponseEnum.ERROR);
        }
    }
    //根据用户名查询用户
    @GetMapping("/username")
    public ApiResponse<String> getUserByUsername(@RequestParam("username")  String username){
        //调用服务查询
        User user=userService.getUserByUsername(username);
        if(user==null){
            System.out.println("查询用户失败");
            return new ApiResponse<>(ResponseEnum.ERROR);
        }
        else {
            System.out.println("查询用户成功"+user);
            return new ApiResponse<>(ResponseEnum.SUCCESS);
        }
    }
    //删除用户
    @DeleteMapping("/username")
    public ApiResponse<String> deleteUser(@RequestParam("username") String username){
        //调用服务层
        try {
            userService.deleteUser(username);
        } catch (Exception e) {
            System.err.println("控制器：删除用户时发生异常：" + e.getMessage());
        }
        return new ApiResponse<>(ResponseEnum.SUCCESS);
    }
}
