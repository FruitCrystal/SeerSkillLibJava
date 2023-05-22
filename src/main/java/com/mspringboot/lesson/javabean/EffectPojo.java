package com.mspringboot.lesson.javabean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("sideeffects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EffectPojo {

@TableId("ID") Integer id;
@TableField("Des") String des;
}
