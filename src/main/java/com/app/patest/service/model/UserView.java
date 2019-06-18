package com.app.patest.service.model;

import com.app.patest.type.handler.RoleEnum;
import lombok.Data;

/**
 * @ClassName: UserView
 * @Author: Yangyang
 * @Date: 2019/6/16
 * @Description: TODO
 */
@Data
public class UserView {
    private int id;
    private String username;
    private long phone;
    private String email;
    private RoleEnum role;
    private String school;
}
