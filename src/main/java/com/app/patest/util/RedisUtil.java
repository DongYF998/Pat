package com.app.patest.util;

import com.app.patest.common.exception.CommonExceptionEnum;
import com.app.patest.common.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisUtil
 * @Author: Yangyang
 * @Date: 2019/6/18
 * @Description: TODO
 */

@Component
public class RedisUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private StringRedisTemplate template = null;

    public boolean setTokenWithTimeOut(String key, String value, long timeout) throws UserException {
        try {
            template.opsForValue().set("token:"+key,value,timeout,TimeUnit.MILLISECONDS);
            return true;
        }catch (Exception e){
            if(e instanceof RedisConnectionFailureException) {
                e.printStackTrace();
                throw new UserException(CommonExceptionEnum.REDIS_SERVER_ERR);
            }else{
                e.printStackTrace();
                throw e;
            }
        }
    }

    public String getToken(String key)throws Exception{
        try {
            return template.opsForValue().get("token:"+key);
        }catch (Exception e){
            throw e;
        }
    }
}
