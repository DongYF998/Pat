package com.app.patest.dao;

import com.app.patest.entity.SelectQuestion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SelectQuestionDaoTest {

    @Autowired
    private SelectQuestionDao dao;

    @Test
    public void testQueryAll(){
        List<SelectQuestion> questions = dao.queryAll();
        System.out.println(questions);
    }

    @Test
    public void testInsertSelectQuestion(){
        SelectQuestion question = new SelectQuestion();
        question.setUid(7);
        question.setTitle("如何创建IDEA项目？");
        question.setAnswerA("A");
        question.setAnswerB("B");
        question.setAnswerC("C");
        question.setAnswerD("D");
        question.setAnswer("A/B");
        question.setLevel("简单");
        question.setScore(5);
        question.setTag("操作系统/数据结构");
        int code = dao.insertSelectQuestion(question);
        System.out.println(code);
    }
}