package com.app.patest.entity;

import lombok.Data;

/**
 * @ClassName: SelectQuestion
 * @Author: Yangyang
 * @Date: 2019/6/18
 * @Description: TODO
 */
@Data
public class SelectQuestion {
    private int uid;
    private int qid;
    private String title;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String answer;
    private String tag;
    private String level;
    private int score;
}
