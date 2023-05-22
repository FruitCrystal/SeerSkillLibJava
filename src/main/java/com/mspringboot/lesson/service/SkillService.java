package com.mspringboot.lesson.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mspringboot.lesson.javabean.SkillPojo;
import com.mspringboot.lesson.javabean.TypePojo;
import com.mspringboot.lesson.mapper.SkillDao;
import com.mspringboot.lesson.mapper.TypeDao;
import com.mspringboot.lesson.utils.Result;
import org.apache.catalina.valves.rewrite.RewriteCond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillService {

    @Autowired
    SkillDao skillDao;

    @Autowired
    TypeDao typeDao;

//按技能名字查询
    public List<SkillPojo> searchPageOfSkillByName(String name,int offset){
        String last = "limit 42 offset "+ offset;
        List<SkillPojo> list = skillDao.selectList(new QueryWrapper<SkillPojo>().like("Name",name).last(last));
        return list;
    }
//按技能id查询
    public SkillPojo doSearchByID(int id){
        SkillPojo skill = skillDao.selectOne(new QueryWrapper<SkillPojo>().eq("ID",id));
        return skill;
    }
    //查询所有技能
    public List<SkillPojo> doSearchAll(){
        return skillDao.selectList(new QueryWrapper<SkillPojo>().select("ID","Name","Type","Power","MaxPP","Priority","Accuracy","CritRate","Des","Category").last("limit 100"));
    }

    //随机生成技能
    public List<SkillPojo> randomSearch(){
        Random random =new Random();
        List<Integer> randomIdList = new ArrayList<>();
        int resultSum =0;
        List<Integer> idList = new ArrayList<>();
        List<SkillPojo> skillList = skillDao.selectList(new QueryWrapper<SkillPojo>().select("ID"));

        for (SkillPojo item: skillList) { //获取完整id列表
            resultSum++;
            idList.add((item.getId()));
            System.out.println(idList.size());//1~24147
        }

        //生成50个随机id
        for (int i=0;i<49;i++){
            randomIdList.add(idList.get(random.nextInt(1,24147)));
        }
        return skillDao.selectBatchIds(randomIdList);
    }
    //按属性查询技能(多条件)
    public List<SkillPojo> doSearchByType(String type,String orderBy,boolean isAsc,int offset){
        String lastSql = "limit 42 offset" + " " + offset;//分页查询,sql8.0以上支持,语法: select * from table limit `每页数量` offset `查询偏移量`,如果偏移量是400,就从第400挑数据开始找
        List<SkillPojo> skill = skillDao.selectList(new QueryWrapper<SkillPojo>().eq("Type",type).orderBy(true,isAsc,orderBy).last(lastSql));
        return skill;
    }

    //按照属性,查找该属性有多少个技能
    public Map<String,Long> searchNumOfType(String type){
        Long num = skillDao.selectCount(new QueryWrapper<SkillPojo>().eq("Type",type).last("limit 42"));
        Map<String,Long> map = new HashMap<>();
        map.put("sum",num);
        return map;
    }
    //查询所有属性
    public List<TypePojo> searchAllType(){
        List<TypePojo> list = typeDao.selectList(new QueryWrapper<TypePojo>().select("DISTINCT  Type"));
        list.remove(86);
        list.add(new TypePojo("\u5c5e\u6027"));
        return list;
    }

    //根据id分页,前端传回单次查询id的最大值,该值默认为0. 这一方法存在局限,不能往回翻页,否则ID会错乱,适合用于往下滑加载更多的页面
    public  List<SkillPojo> searchPage(String name,int maxID){
        return  skillDao.selectList(new QueryWrapper<SkillPojo>().like("Name",name).orderBy(true,true,"ID").gt("ID",maxID).last("limit 42"));
        //使用ID条件来进行分页,响应速度更快,但是前端仍然无法获得数据总数,仍然需要两个接口的协同
    }

    //按照名字,查找该属性有多少个技能
    public Map<String,Long> searchNumOfName(String name){
        Long num = skillDao.selectCount(new QueryWrapper<SkillPojo>().like("Name",name));
        Map<String,Long> map = new HashMap<>();
        map.put("sum",num);
        return map;
    }

    //新增自定义技能
    public Result addSkill(SkillPojo pojo){
        int rows = skillDao.insert(pojo);
        int state = 0;
        if(rows>0){
            return Result.dealState(200);
        }else {
            return Result.dealState(404);
        }
    }
    //查询自定义技能
    public List<SkillPojo> searchDiySkill(){
        return skillDao.selectList( new QueryWrapper<SkillPojo>().select().gt("ID",49999));
    }

    //删除自定义技能
    public int deleteSkill(int id){
        return skillDao.delete(new QueryWrapper<SkillPojo>().eq("ID",id));
    }


    //查询每个属性的技能数量
    public  List<Map<String, Object>> getSumOfAllType(){
//        Map<String,Long> map = new HashMap<>();
//        List<TypePojo> typePojos = typeDao.selectList(new QueryWrapper<TypePojo>().select("DISTINCT Type").orderBy(true, true, "Type"));
//        for (TypePojo t :typePojos){
//            System.out.println(t.getType()+":");
//        }
            return skillDao.getTypeCount();

    }

    //按照技能持有者的id或者名字查找相关技能
    public List<SkillPojo> getSkillByIdOrName(String keyWord){
        if( keyWord.matches("^[0-9]+$")){
            System.out.println("是ID");
            List<SkillPojo> skillPojoList = skillDao.selectList(new QueryWrapper<SkillPojo>().last("WHERE FIND_IN_SET("+keyWord+",PetsID)"));
            return skillPojoList;
           /* List<SkillPojo> result = new ArrayList<>();
            for (SkillPojo s: skillPojoList) {
                System.out.println(Arrays.toString(s.getPetsID().split(",")));
                if(Arrays.stream(s.getPetsID().split(",")).toList().contains(keyWord)){
                    result.add(s);
                }else {
                    return null;
                }
            }
            return result;*/
//            return skillDao.getSkillById(keyWord);
        }else {
            System.out.println("是名字");
            return skillDao.selectList(new QueryWrapper<SkillPojo>().like("PetsName",keyWord));
//            return skillDao.getSkillByName(keyWord);
        }
    }
}
