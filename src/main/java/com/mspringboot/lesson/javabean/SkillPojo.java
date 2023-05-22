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

    @TableField("Priority") Integer priority;//����

    @TableField("Accuracy") String accuracy;//��׼��

    @TableField("CritRate") String critRate;//������

    @TableField("Des") String des;//����

    @TableField("PetsID") String petsID;//������id

    @TableField("PetsName") String petsName;//����������

    @TableField("SideEffect") String sideEffect;
}
