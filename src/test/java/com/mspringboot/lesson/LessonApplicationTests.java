package com.mspringboot.lesson;

import com.mspringboot.lesson.service.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LessonApplicationTests {

    @Autowired
    SkillService skillService;
    @Test
    void contextLoads() {
        System.out.println(skillService.getSumOfAllType());
        System.out.println("你好！");
    }

}
