package com.mspringboot.lesson.controller;

import com.mspringboot.lesson.javabean.EffectPojo;
import com.mspringboot.lesson.mapper.EffectDao;
import com.mspringboot.lesson.service.SideEffectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/effect")
public class SideEffectController {

    @Autowired
    SideEffectService sideEffectService;

    @RequestMapping(value = "/getEffect",method = RequestMethod.GET)
    public List<EffectPojo> getEffect(){
        return sideEffectService.getEffect();
    }

    @RequestMapping(value = "getOneEffectByID")
    public EffectPojo getOneEffect(int id){
        return sideEffectService.getOneEffect(id);
    }

    @RequestMapping(value = "getEffectByDes")//根据效果描述模糊搜索效果
    public List<EffectPojo> getEffectByDes(String des){
        return sideEffectService.getEffectByDes(des);
    }

    //寻找某个属性下具备某种效果的技能列表


}
