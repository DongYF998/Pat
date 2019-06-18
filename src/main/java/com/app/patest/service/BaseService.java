package com.app.patest.service;

import com.app.patest.entity.User;
import com.app.patest.service.model.UserView;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName: BaseService
 * @Author: Yangyang
 * @Date: 2019/6/16
 * @Description: TODO
 */
public class BaseService {

    public UserView transToUserView(User user){
        if (user == null)
            return null;
        UserView userView = new UserView();
        BeanUtils.copyProperties(user,userView);
        return userView;
    }
}
