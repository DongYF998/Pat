package com.app.patest.util;

import com.app.patest.common.exception.CommonExceptionEnum;
import com.app.patest.common.exception.UserException;
import com.app.patest.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TokenUtil
 * @Author: Yangyang
 * @Date: 2019/6/14
 * @Description: 通过Username 获得Token
 */

@Component
public class TokenUtil {

    /**
     * 获得用户token
     * @param user
     * @return
     */
    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(user.getUsername()). // 将UserName 保存在token中
                withClaim("role",user.getRole()). //将用户角色放入Token
                withClaim("create_time",System.currentTimeMillis()).   //放入一个时间戳来生成一个不同的token
                sign(Algorithm.HMAC256(user.getPassword())); // 将密码作为密钥
        return token;
    }

    /**
     * 通过Token 获得Username
     * @param token
     * @return
     */
    public String getUsername(String token) throws UserException {
        try {
            return JWT.decode(token).getAudience().get(0);
        }catch (Exception e){
            throw new UserException(CommonExceptionEnum.DECODE_FAIL);
        }

    }
}
