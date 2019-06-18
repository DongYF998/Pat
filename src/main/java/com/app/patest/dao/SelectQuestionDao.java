package com.app.patest.dao;


import com.app.patest.entity.SelectQuestion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectQuestionDao {
    List<SelectQuestion> queryAll();

    int insertSelectQuestion(SelectQuestion question);
}
