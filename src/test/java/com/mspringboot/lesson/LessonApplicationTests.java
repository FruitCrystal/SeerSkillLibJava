package com.mspringboot.lesson;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mspringboot.lesson.javabean.SkillPojo;
import com.mspringboot.lesson.mapper.SkillDao;
import com.mspringboot.lesson.service.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LessonApplicationTests {

    @Autowired
    SkillService skillService;
    @Autowired
    SkillDao skillDao;
    @Test
    void contextLoads() {
        System.out.println(skillService.getSumOfAllType());
        System.out.println("你好！");
    }

    @Test
    void test(){
        System.out.println(skillDao.selectList(new QueryWrapper<SkillPojo>().select("ID")));
    }

}
