package com.mspringboot.lesson.mapper;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mspringboot.lesson.javabean.SkillPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SkillDao extends BaseMapper<SkillPojo> {

//�ù�����MybatisPlus�޷�ʵ�֣��ʻص�Mybatis����ΪMybatisPlus�ǻ���Mybatis��
        @Select("SELECT Type,COUNT(`Name`) AS  `sum` FROM seerskills GROUP BY Type  ORDER BY Count(`Name`) DESC")
        List<Map<String, Object>> getTypeCount();

}
