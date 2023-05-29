package com.mspringboot.lesson.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mspringboot.lesson.javabean.CollectionPojo;
import com.mspringboot.lesson.mapper.CollectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionService {
    @Autowired
    CollectionDao collectionDao;

    public int addIntoCollection(CollectionPojo item){
        return collectionDao.insert(item);
    }

    public int deleteFromCollection(int id){
        return collectionDao.delete(new QueryWrapper<CollectionPojo>().eq("ID",id));
    }

    public List<Integer> getCollection(){
        List<CollectionPojo> collectionPojoList = collectionDao.selectList(new QueryWrapper<CollectionPojo>().select("ID"));
        List<Integer> idList = new ArrayList<>();
        for(CollectionPojo pojo:collectionPojoList){
            idList.add(pojo.getID());
        }
        return idList;
    }
}
