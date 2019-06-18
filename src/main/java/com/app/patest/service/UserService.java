package com.app.patest.service;

import com.app.patest.common.exception.UserException;
import com.app.patest.entity.User;
import com.app.patest.service.model.UserView;

public interface UserService {
    boolean isUsernameExist(String username);

    User login(String username, String password) throws UserException;

    int register(User user) throws UserException;
}
