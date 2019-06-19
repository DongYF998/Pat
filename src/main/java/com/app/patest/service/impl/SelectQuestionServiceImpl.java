package com.app.patest.service.impl;

import com.app.patest.common.exception.CommonExceptionEnum;
import com.app.patest.common.exception.UserException;
import com.app.patest.dao.SelectQuestionDao;
import com.app.patest.dao.UserDao;
import com.app.patest.entity.SelectQuestion;
import com.app.patest.entity.User;
import com.app.patest.service.SelectQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: SelectQuestionServiceImpl
 * @Author: Yangyang
 * @Date: 2019/6/18
 * @Description: TODO
 */
@Service
public class SelectQuestionServiceImpl implements SelectQuestionService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SelectQuestionDao questionDao;

    @Override
    public int InsertQuestion(SelectQuestion question) throws UserException {
        User user = userDao.queryUserById(question.getUid());
        if(user.getRole() !=1 && user.getRole() != 2){
            return 0;
        }
        int code = questionDao.insertSelectQuestion(question);
        if(code != 0){
            return code;
        }
        return 0;
    }

    @Override
    public List<SelectQuestion> queryAll() {
        return questionDao.queryAll();
    }
}
