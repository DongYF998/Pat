package com.app.patest.dao;

import com.app.patest.entity.User;
import com.app.patest.type.handler.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testQueryAll(){
        List<User> users = userDao.queryAll();
        for (User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("coverse");
        user.setPassword("19980325");
        user.setSchool("西南石油大学");
        user.setRole(1);
        user.setEmail("329640258@qq.vom");
        user.setPhone(13100008888L);
        userDao.insertUser(user);

    }

    @Test
    public void testQueryUserById(){
        User user = userDao.queryUserById(7);
        System.out.println(user);
    }
}