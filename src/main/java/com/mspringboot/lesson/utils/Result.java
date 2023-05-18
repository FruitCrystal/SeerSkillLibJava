package com.mspringboot.lesson.utils;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Result {
    String state;

    String msg;

    public static  Result dealState(int state){
        if(state == 200)
            return new Result("ok","响应成功");
        else if(state == 404)
            return new Result("no","找不到资源 ");
        else if(state ==500)
            return new Result("no","服务端出错");
        else
            return new Result("no","响应失败");
    }
}
