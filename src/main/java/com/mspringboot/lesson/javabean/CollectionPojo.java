package com.mspringboot.lesson.javabean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("collection")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CollectionPojo {

    @TableId("ID")
    Integer ID;
}
