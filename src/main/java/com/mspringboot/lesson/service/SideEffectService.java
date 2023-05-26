package com.mspringboot.lesson.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mspringboot.lesson.javabean.EffectPojo;
import com.mspringboot.lesson.mapper.EffectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SideEffectService {

    @Autowired
    EffectDao effectDao;

    public List<EffectPojo> getEffect(){
        return effectDao.selectList(new QueryWrapper<EffectPojo>().select("ID","Des"));
    }

    public EffectPojo getOneEffect(int id){
        return effectDao.selectOne(new QueryWrapper<EffectPojo>().eq("ID",id));
    }

    public List<EffectPojo> getEffectByDes(String des){
        return effectDao.selectList(new QueryWrapper<EffectPojo>().like("Des",des));
    }


}
