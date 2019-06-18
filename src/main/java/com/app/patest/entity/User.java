package com.app.patest.entity;

import com.app.patest.type.handler.RoleEnum;
import lombok.Data;

/**
 * @ClassName: User
 * @Author: Yangyang
 * @Date: 2019/6/14
 * @Description: TODO
 */

@Data
public class User {
    private int uid;
    private String username;
    private String password;
    private long phone;
    private String email;
    private int role;
    private String school;
}
