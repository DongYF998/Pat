package com.app.patest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(basePackages = {"com.app.patest.dao"}, annotationClass = Repository.class)
public class PatestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatestApplication.class, args);
    }

}
