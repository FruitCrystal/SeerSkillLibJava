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
            return new Result("ok","��Ӧ�ɹ�");
        else if(state == 404)
            return new Result("no","�Ҳ�����Դ ");
        else if(state ==500)
            return new Result("no","����˳���");
        else
            return new Result("no","��Ӧʧ��");
    }
}
