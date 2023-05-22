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
@AllArgsConstructor
@TableName(value = "seerskills")
public class SkillPojo {
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

    @TableField("SideEffect") String sideEffect;
}
