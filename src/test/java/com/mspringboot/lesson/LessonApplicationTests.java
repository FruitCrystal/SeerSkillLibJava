package com.mspringboot.lesson;

import com.mspringboot.lesson.javabean.SkillPojo;
import com.mspringboot.lesson.service.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LessonApplicationTests {

    @Autowired
    SkillService skillService;
    @Test
    void contextLoads() {
        System.out.println(skillService.getSumOfAllType());
        System.out.println("你好！");
    }

    @Test
    void test(){
        List<SkillPojo> skill = skillService.getSkillByIdOrName("朱");
        System.out.println(skill);
    }

}
