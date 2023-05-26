package com.mspringboot.lesson;

import com.mspringboot.lesson.javabean.SkillPojo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootApplication
@MapperScan("com.mspringboot.lesson.mapper")
public class LessonApplication {

    public static void main(String[] args) {


        SpringApplication.run(LessonApplication.class, args);
    }

}
