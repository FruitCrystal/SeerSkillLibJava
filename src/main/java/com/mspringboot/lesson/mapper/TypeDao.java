package com.mspringboot.lesson.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mspringboot.lesson.javabean.TypePojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TypeDao extends BaseMapper<TypePojo> {
}
