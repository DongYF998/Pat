package com.app.patest.service;

import com.app.patest.common.exception.UserException;
import com.app.patest.entity.SelectQuestion;

import java.util.List;

/**
 * @ClassName: SelectQuestionService
 * @Author: Yangyang
 * @Date: 2019/6/18
 * @Description: TODO
 */
public interface SelectQuestionService {

    int InsertQuestion(SelectQuestion question) throws UserException;

    List<SelectQuestion> queryAll();
}
