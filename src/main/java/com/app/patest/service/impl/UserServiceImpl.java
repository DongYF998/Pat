package com.app.patest.service.impl;

import com.app.patest.common.exception.CommonExceptionEnum;
import com.app.patest.common.exception.UserException;
import com.app.patest.dao.UserDao;
import com.app.patest.entity.User;
import com.app.patest.service.BaseService;
import com.app.patest.service.UserService;
import com.app.patest.service.model.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Author: Yangyang
 * @Date: 2019/6/16
 * @Description: TODO
 */
@Service
public class UserServiceImpl extends BaseService implements UserService{
    @Autowired
    private UserDao dao;

    @Override
    public boolean isUsernameExist(String username) {
        return dao.isUsernameExist(username) == 0 ? false:true;
    }

    @Override
    public User login(String username, String password) throws UserException {
        User user = dao.queryUserByUsername(username);
        if(user == null)
            throw new UserException(CommonExceptionEnum.USER_NOT_EXIST);
        if(!user.getPassword().equals(password))
            throw new UserException(CommonExceptionEnum.PASSWPRD_ERR);
        System.out.println(user);
        return user;
    }

    @Override
    public int register(User user) throws UserException {
        if(dao.isUsernameExist(user.getUsername()) != 0)
            throw new UserException(CommonExceptionEnum.USERNAME_EXIST);
        if(user.getRole() == 2)
            throw new UserException(CommonExceptionEnum.ILLEGAL_USER);
        int code = dao.insertUser(user);
        return code;
    }
}
