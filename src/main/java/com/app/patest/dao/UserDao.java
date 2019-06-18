package com.app.patest.dao;

import com.app.patest.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<User> queryAll();

    int isUsernameExist(String username);

    User queryUserByUsername(String username);

    User queryUserById(int uid);

    int insertUser(User user);
}
