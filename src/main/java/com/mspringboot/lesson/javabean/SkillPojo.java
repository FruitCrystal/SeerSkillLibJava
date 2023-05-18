package com.mspringboot.lesson.javabean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@TableName(value = "seerskills")
public class SkillPojo {
    @TableField(exist = false)
    boolean isDIY;//exist=false�������ֶ��ڱ��в����ڣ��������������ı�ʶ
    @TableId("ID") Integer id;
    @TableField("Name") String name;
    @TableField("Type") String type;

    @TableField("Category") String category;
    @TableField("Power") Integer power;

    @TableField("MaxPP") Integer maxPP;

    @TableField("Priority") Integer priority;//����

    @TableField("Accuracy") String accuracy;//��׼��

    @TableField("CritRate") String critRate;//������

    @TableField("Des") String des;//����

    @TableField("PetsID") String petsID;//������id

    @TableField("PetsName") String petsName;//����������

    public boolean isDIY() {
        return isDIY;
    }

    public void setDIY(boolean DIY) {
        isDIY = DIY;
    }

    public SkillPojo(Integer id, String name, String type, String category, Integer power, Integer maxPP, Integer priority, String accuracy, String critRate, String des, String petsID, String petsName) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.category = category;
        this.power = power;
        this.maxPP = maxPP;
        this.priority = priority;
        this.accuracy = accuracy;
        this.critRate = critRate;
        this.des = des;
        this.petsID = petsID;
        this.petsName = petsName;
    }
}
