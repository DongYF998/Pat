package com.app.patest.controller;

import com.app.patest.annotation.PassToken;
import com.app.patest.annotation.TeacherToken;
import com.app.patest.annotation.UserToken;
import com.app.patest.common.exception.CommonExceptionEnum;
import com.app.patest.common.exception.UserException;
import com.app.patest.common.type.CommonReturnType;
import com.app.patest.entity.User;
import com.app.patest.service.UserService;
import com.app.patest.service.model.UserView;
import com.app.patest.util.RedisUtil;
import com.app.patest.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserController
 * @Author: Yangyang
 * @Date: 2019/6/16
 * @Description: TODO
 */

@RestController
@RequestMapping("/api/account")
@CrossOrigin
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisUtil redisUtil;

    @PassToken
    @PostMapping("/login")
    public CommonReturnType login(@RequestBody User user) throws UserException {
        if(user == null)
            throw new UserException(CommonExceptionEnum.UNKNOWS_PRAMAR_ERR);
        if(user.getUsername().equals("")|| user.getUsername() == null)
            throw new UserException(CommonExceptionEnum.USERNAME_EMPTY);
        if(user.getPassword().equals("")|| user.getPassword() == null)
            throw new UserException(CommonExceptionEnum.PASSWORD_EMPTY);
        User userView = userService.login(user.getUsername(),user.getPassword());
        if(userView == null)
            throw new UserException(CommonExceptionEnum.USER_NOT_EXIST);

        String token = tokenUtil.getToken(userView);
        redisUtil.setTokenWithTimeOut(user.getUsername(),token,1000*60*60*2);
        Map<String,Object> data = new HashMap<>();
        data.put("token",token);
        data.put("user",userView);
        return new CommonReturnType(data);
    }

    @PassToken
    @PostMapping("/register")
    public CommonReturnType register(@RequestBody User user) throws UserException {
        int code = userService.register(user);
        if(code == 0){
            return new CommonReturnType("注册失败");
        }
        return new CommonReturnType("注册成功");
    }

    @PassToken
    @GetMapping("/checkUsername")
    public CommonReturnType checkUsername(String username){
        return new CommonReturnType(userService.isUsernameExist(username));
    }

    @TeacherToken
    @GetMapping("/test")
    public String test(){
        return "通过教师认证";
    }

    @PassToken
    @GetMapping("/testServer")
    public CommonReturnType testService(){
        return new CommonReturnType();
    }
}
