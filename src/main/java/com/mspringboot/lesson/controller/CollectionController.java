package com.mspringboot.lesson.controller;

import com.mspringboot.lesson.javabean.CollectionPojo;
import com.mspringboot.lesson.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    @RequestMapping(value = "/add")
    public int add(int id){
        return collectionService.addIntoCollection(new CollectionPojo(id));
    }
    @RequestMapping(value = "/delete")
    public int delete(int id){
        return collectionService.deleteFromCollection(id);
    }
    @RequestMapping(value = "/get")
    public List<Integer> get(){
        return collectionService.getCollection();
    }
}
