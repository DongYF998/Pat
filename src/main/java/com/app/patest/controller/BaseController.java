package com.app.patest.controller;

import com.app.patest.common.exception.UserException;
import com.app.patest.common.type.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception e) {
        Map<String, Object> responseData = new HashMap<>();
        CommonReturnType commonReturnType = new CommonReturnType();
        if (e instanceof UserException) {
            //强制转化捕获的错误为UserException
            UserException userException = (UserException) e;
            //将错误信息转化为通用的上传格式
            commonReturnType.setMsg("fail");
            commonReturnType.setCode(userException.getExceptionCode());
            commonReturnType.setData(userException.getExceptionMsg());

        } else {
            e.printStackTrace();
            commonReturnType.setCode(e.hashCode());
            commonReturnType.setMsg(e.getMessage());
            commonReturnType.setData(e.getLocalizedMessage());
        }
        return commonReturnType;

    }
}
