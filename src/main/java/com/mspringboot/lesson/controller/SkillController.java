package com.mspringboot.lesson.controller;


import com.mspringboot.lesson.javabean.SkillPojo;
import com.mspringboot.lesson.javabean.TypePojo;
import com.mspringboot.lesson.service.SkillService;
import com.mspringboot.lesson.utils.Result;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
public class SkillController {

    @Autowired
    SkillService skillService;


    @RequestMapping(value = "/searchRandomly")
    public List<SkillPojo> searchRandomly(){
        return skillService.randomSearch();
    }


    @RequestMapping(value = "/searchByName")
    public List<SkillPojo> searchBySkillName(@RequestParam(value = "name") String name,@RequestParam(value = "offset")int offset){
        if(Objects.equals(name, "")){
            return null;
        }
        return skillService.searchPageOfSkillByName(name,offset);
    }

    //����id��ҳ,ǰ�˴��ص��β�ѯid�����ֵ,��ֵĬ��Ϊ0
    @RequestMapping(value = "/searchPageOfSkill")
    public List<SkillPojo> searchByName(@RequestParam(value = "name") String name,@RequestParam(value = "maxID")int maxID){
        return skillService.searchPage(name,maxID);
    }

    //����ID��������
    @RequestMapping(value = "/searchByID")
    public SkillPojo searchByID(int id){
        return skillService.doSearchByID(id);
    }

    //�������Ʋ�ѯ��������
    @RequestMapping(value = "/getSumByName")
    public Map<String,Long> getSumByName(String name){
        return skillService.searchNumOfName(name);
    }
    //��ȡ��������
    @RequestMapping(value = "getAllType")
    public List<TypePojo> getAllType(){
        return skillService.searchAllType();
    }

    //�����Բ�ѯ
    @RequestMapping(value = "searchByType")
    public List<SkillPojo> getAllByType(@RequestParam(value = "type") String type, @RequestParam(value = "orderBy") String orderBy,@RequestParam(value = "isAsc")boolean isasc,@RequestParam(value = "offset") int offset){
        if(Objects.equals(type, "����")){
            System.out.println(type);
            return skillService.doSearchByType("--",orderBy,isasc,offset);
        }
        return skillService.doSearchByType(type,orderBy,isasc,offset);
    }

    //��������,���Ҹ������ж��ٸ�����
    @RequestMapping(value = "/getSumOfType")
    public Map<String,Long> getSumOfType(String type){
        if(Objects.equals(type, "����")){
            System.out.println(type);
            return skillService.searchNumOfType("--");
        }
        return skillService.searchNumOfType(type);
    }

    @RequestMapping(value = "/addSkill", method = RequestMethod.POST)
        public Result addSkill(HttpServletRequest request){
//        Map<String,Integer> map = new HashMap<>();
        String name = request.getParameter("skillName");
        String category = request.getParameter("skillCategory");
        String type = request.getParameter("skillType");
        if(type.equals("����")){
            type="--";
        }
        int pp = Integer.parseInt(request.getParameter("skillPP"));
        String accuracy = request.getParameter("skillAccuracy");
        int priority = Integer.parseInt(request.getParameter("skillPriority"));
        int power = Integer.parseInt(request.getParameter("skillPower"));
        String critRate = request.getParameter("skillCritRate");
        String desList = request.getParameter("effect");
        //����des����
        String desString ;
        if (desList.equals("")) {
            desString="--";
        }else {
            desString=desList.replace(",","\uff1b");
        }
        System.out.println(desString);
        int ID =Math.round((float) (Math.abs(name.hashCode()+category.hashCode()+critRate.hashCode()+desList.hashCode()+accuracy.hashCode()+type.hashCode()+power*10000+pp*10000+priority*10000)))+49999;
        SkillPojo diySkill = new SkillPojo(ID,name,type,category,power,pp,priority,accuracy,critRate,desString,"","","");
//        SkillPojo diySkill = new SkillPojo(ID,name)
        System.out.println(diySkill);
        System.out.println("ID="+ID);
            return skillService.addSkill(diySkill);
//        return null;
    }

    //��ѯ�Զ��弼��
    @RequestMapping(value = "getPageOfDiySkill")
    public List<SkillPojo> getPageOf(){
        return skillService.searchDiySkill();
    }

    //ɾ������
    @RequestMapping(value = "deleteSkill")
    public int delSkill(int id){
        if(id<49999){
            return 0;
        }else {
            skillService.deleteSkill(id);
            return id;
        }
    }

    //��ѯÿ��ϵ�ж��ٸ�����
    @RequestMapping(value = "getSumOfAllType")
    public List<Map<String, Object>> getSumOfAllType(){
        return skillService.getSumOfAllType();
    }

    //��ѯÿ��ϵ�ж��ٸ�����
    @RequestMapping(value = "getSkillByIdOrName")
    public List<SkillPojo> getSkillByIdOrName(String val){
        return skillService.getSkillByIdOrName(val);
    }

    //���ݼ���Ч����ѯ
    @RequestMapping(value = "getSkillByEffect")
    public List<SkillPojo> getSkillsBySideEffect(@RequestParam(value = "id") int id, @RequestParam(value = "page") int page){
        return skillService.getSkillsByEffect(id,page);
    }

    @RequestMapping(value = "getSumOfSkillByEffect")
    public Long getSumOfSkillByEffect(int id){
        return skillService.getSumOfSkillByEffect(id);
    }

    @RequestMapping(value = "getSkillByEffectAndType")
    public List<SkillPojo> getSkillByEffectAndType(int id,String type){
        return skillService.getSkillsByEffectAndType(id,type);
    }
}
