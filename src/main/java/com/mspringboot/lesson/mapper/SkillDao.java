package com.mspringboot.lesson.mapper;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mspringboot.lesson.javabean.SkillPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkillDao extends BaseMapper<SkillPojo> {

}
