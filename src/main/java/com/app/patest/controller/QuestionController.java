package com.app.patest.controller;

import com.app.patest.annotation.TeacherToken;
import com.app.patest.common.exception.CommonExceptionEnum;
import com.app.patest.common.exception.UserException;
import com.app.patest.common.type.CommonReturnType;
import com.app.patest.dao.UserDao;
import com.app.patest.entity.SelectQuestion;
import com.app.patest.entity.User;
import com.app.patest.service.SelectQuestionService;
import com.app.patest.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: QuestionController
 * @Author: Yangyang
 * @Date: 2019/6/18
 * @Description: TODO
 */
@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private SelectQuestionService questionService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserDao userDao;

    @TeacherToken
    @PostMapping("/uploadSelect")
    public CommonReturnType uploadQuestion(@RequestBody SelectQuestion question, HttpServletRequest request) throws UserException {
        System.out.println(question);
        String token = request.getHeader("token");
        String username = tokenUtil.getUsername(token);
        User user = userDao.queryUserByUsername(username);
        question.setUid(user.getUid());
        if(questionService.InsertQuestion(question) != 1){
            return new CommonReturnType("上传失败");
        }
        return new CommonReturnType("上传成功");
    }

    @GetMapping("/queryAll")
    public CommonReturnType queryAll(){
        return new CommonReturnType(questionService.queryAll());
    }
}
