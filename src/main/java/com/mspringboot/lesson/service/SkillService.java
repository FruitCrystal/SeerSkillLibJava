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

//���������ֲ�ѯ
    public List<SkillPojo> searchPageOfSkillByName(String name,int offset){
        String last = "limit 42 offset "+ offset;
        List<SkillPojo> list = skillDao.selectList(new QueryWrapper<SkillPojo>().like("Name",name).last(last));
        return list;
    }
//������id��ѯ
    public SkillPojo doSearchByID(int id){
        SkillPojo skill = skillDao.selectOne(new QueryWrapper<SkillPojo>().eq("ID",id));
        return skill;
    }
    //��ѯ���м���
    public List<SkillPojo> doSearchAll(){
        return skillDao.selectList(new QueryWrapper<SkillPojo>().select("ID","Name","Type","Power","MaxPP","Priority","Accuracy","CritRate","Des","Category").last("limit 100"));
    }

    //������ɼ���
    public List<SkillPojo> randomSearch(){
        Random random =new Random();
        List<Integer> randomIdList = new ArrayList<>();
        int resultSum =0;
        List<Integer> idList = new ArrayList<>();
        List<SkillPojo> skillList = skillDao.selectList(new QueryWrapper<SkillPojo>().select("ID"));

        for (SkillPojo item: skillList) { //��ȡ����id�б�
            resultSum++;
            idList.add((item.getId()));
            System.out.println(idList.size());//1~24147
        }

        //����50�����id
        for (int i=0;i<49;i++){
            randomIdList.add(idList.get(random.nextInt(1,24147)));
        }
        return skillDao.selectBatchIds(randomIdList);
    }
    //�����Բ�ѯ����(������)
    public List<SkillPojo> doSearchByType(String type,String orderBy,boolean isAsc,int offset){
        String lastSql = "limit 42 offset" + " " + offset;//��ҳ��ѯ,sql8.0����֧��,�﷨: select * from table limit `ÿҳ����` offset `��ѯƫ����`,���ƫ������400,�ʹӵ�400�����ݿ�ʼ��
        List<SkillPojo> skill = skillDao.selectList(new QueryWrapper<SkillPojo>().eq("Type",type).orderBy(true,isAsc,orderBy).last(lastSql));
        return skill;
    }

    //��������,���Ҹ������ж��ٸ�����
    public Map<String,Long> searchNumOfType(String type){
        Long num = skillDao.selectCount(new QueryWrapper<SkillPojo>().eq("Type",type).last("limit 42"));
        Map<String,Long> map = new HashMap<>();
        map.put("sum",num);
        return map;
    }
    //��ѯ��������
    public List<TypePojo> searchAllType(){
        List<TypePojo> list = typeDao.selectList(new QueryWrapper<TypePojo>().select("DISTINCT  Type"));
        list.remove(86);
        list.add(new TypePojo("\u5c5e\u6027"));
        return list;
    }

    //����id��ҳ,ǰ�˴��ص��β�ѯid�����ֵ,��ֵĬ��Ϊ0. ��һ�������ھ���,�������ط�ҳ,����ID�����,�ʺ��������»����ظ����ҳ��
    public  List<SkillPojo> searchPage(String name,int maxID){
        return  skillDao.selectList(new QueryWrapper<SkillPojo>().like("Name",name).orderBy(true,true,"ID").gt("ID",maxID).last("limit 42"));
        //ʹ��ID���������з�ҳ,��Ӧ�ٶȸ���,����ǰ����Ȼ�޷������������,��Ȼ��Ҫ�����ӿڵ�Эͬ
    }

    //��������,���Ҹ������ж��ٸ�����
    public Map<String,Long> searchNumOfName(String name){
        Long num = skillDao.selectCount(new QueryWrapper<SkillPojo>().like("Name",name));
        Map<String,Long> map = new HashMap<>();
        map.put("sum",num);
        return map;
    }

    //�����Զ��弼��
    public Result addSkill(SkillPojo pojo){
        int rows = skillDao.insert(pojo);
        int state = 0;
        if(rows>0){
            return Result.dealState(200);
        }else {
            return Result.dealState(404);
        }
    }
    //��ѯ�Զ��弼��
    public List<SkillPojo> searchDiySkill(){
        return skillDao.selectList( new QueryWrapper<SkillPojo>().select().gt("ID",49999));
    }

    //ɾ���Զ��弼��
    public int deleteSkill(int id){
        return skillDao.delete(new QueryWrapper<SkillPojo>().eq("ID",id));
    }


    //��ѯÿ�����Եļ�������
    public  List<Map<String, Object>> getSumOfAllType(){
//        Map<String,Long> map = new HashMap<>();
//        List<TypePojo> typePojos = typeDao.selectList(new QueryWrapper<TypePojo>().select("DISTINCT Type").orderBy(true, true, "Type"));
//        for (TypePojo t :typePojos){
//            System.out.println(t.getType()+":");
//        }
            return skillDao.getTypeCount();

    }

    //���ռ��ܳ����ߵ�id�������ֲ�����ؼ���
    public List<SkillPojo> getSkillByIdOrName(String keyWord){
        if( keyWord.matches("^[0-9]+$")){
            System.out.println("��ID");
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
            System.out.println("������");
            return skillDao.selectList(new QueryWrapper<SkillPojo>().like("PetsName",keyWord));
//            return skillDao.getSkillByName(keyWord);
        }
    }
}
