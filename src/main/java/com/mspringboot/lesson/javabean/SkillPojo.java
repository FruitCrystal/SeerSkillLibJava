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
    boolean isDIY;//exist=false表明该字段在表中不存在，是属于这个对象的标识
    @TableId("ID") Integer id;
    @TableField("Name") String name;
    @TableField("Type") String type;

    @TableField("Category") String category;
    @TableField("Power") Integer power;

    @TableField("MaxPP") Integer maxPP;

    @TableField("Priority") Integer priority;//先制

    @TableField("Accuracy") String accuracy;//精准度

    @TableField("CritRate") String critRate;//暴击率

    @TableField("Des") String des;//描述

    @TableField("PetsID") String petsID;//持有者id

    @TableField("PetsName") String petsName;//持有者名字

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
