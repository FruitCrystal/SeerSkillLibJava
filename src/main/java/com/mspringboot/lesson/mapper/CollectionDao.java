package com.mspringboot.lesson.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mspringboot.lesson.javabean.CollectionPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectionDao extends BaseMapper<CollectionPojo> {
}
