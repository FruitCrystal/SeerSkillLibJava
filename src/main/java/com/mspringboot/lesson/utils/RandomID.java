package com.mspringboot.lesson.utils;

import com.mspringboot.lesson.javabean.SkillPojo;

public class RandomID {
    public static  int randomID(SkillPojo pojo){
        return  pojo.hashCode();
    }
}
