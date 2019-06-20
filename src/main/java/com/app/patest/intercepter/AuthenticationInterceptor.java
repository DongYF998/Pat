package com.app.patest.intercepter;

import com.app.patest.annotation.AdminToken;
import com.app.patest.annotation.PassToken;
import com.app.patest.annotation.TeacherToken;
import com.app.patest.annotation.UserToken;
import com.app.patest.common.exception.CommonExceptionEnum;
import com.app.patest.common.exception.UserException;
import com.app.patest.dao.UserDao;
import com.app.patest.entity.User;
import com.app.patest.util.RedisUtil;
import com.app.patest.util.TokenUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName: AuthenticationInterceptor
 * @Author: Yangyang
 * @Date: 2019/6/18
 * @Description: TODO
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        //非映射方法直接通过
        if(! (handler instanceof HandlerMethod)){
            return true;
        }

        //通过反射机制获得相关的方法和方法上的注解
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //如果含有@PassToken 直接通过
        if(method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }

        //验证管理员接口@AdminToken
        if(method.isAnnotationPresent(AdminToken.class)){
            AdminToken adminToken = method.getAnnotation(AdminToken.class);
            if(adminToken.required()){
                String username = JWT.decode(token).getAudience().get(0);
                User user = userDao.queryUserByUsername(username);
                if(user.getRole() != 2){
                    throw new UserException(CommonExceptionEnum.NO_TEACHER_PERMISSION);
                }
                return handler(token);
            }
        }

        //验证教师接口@TeacherToken
        if(method.isAnnotationPresent(TeacherToken.class)){
            TeacherToken teacherToken = method.getAnnotation(TeacherToken.class);
            if(teacherToken.required()){
                //验证Token有效性
                String username = JWT.decode(token).getAudience().get(0);
                User user = userDao.queryUserByUsername(username);
                if(user.getRole() == 0){
                    throw new UserException(CommonExceptionEnum.NO_TEACHER_PERMISSION);
                }
                return handler(token);
            }
        }

        //验证一般用户接口@UserToken
        if(method.isAnnotationPresent(UserToken.class)){
            UserToken userToken = method.getAnnotation(UserToken.class);
            if(userToken.required()){
                return handler(token);
            }
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    public boolean handler(String token) throws Exception {
        if(token==null||token.equals(""))
            throw new UserException(CommonExceptionEnum.NO_PERMISSION);

        //验证用户名
        String username = tokenUtil.getUsername(token);
        if(userDao.isUsernameExist(username) == 0)
            throw new UserException(CommonExceptionEnum.USER_NOT_EXIST);

        //验证Token时效性
        if(redisUtil.getToken(username) == null)
            throw new UserException(CommonExceptionEnum.TIME_OUT);
        if(!redisUtil.getToken(username).equals(token))
            throw new UserException(CommonExceptionEnum.VERIFY_OUTTIME);

        User user = userDao.queryUserByUsername(username);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();

        //验证token有效性
        try {
            verifier.verify(token);
        }catch (JWTVerificationException e){
            throw new UserException(CommonExceptionEnum.ILLEGAL_USER);
        }
        //重新设置验证有效时间
        redisUtil.setTokenWithTimeOut(username,token,1000*60*60*2);
        return true;
    }
}
