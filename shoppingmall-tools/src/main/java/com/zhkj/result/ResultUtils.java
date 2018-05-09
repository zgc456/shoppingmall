package com.zhkj.result;

import java.util.List;

/**
 * 定义统一的返回值
 */
public class ResultUtils<T> {
    public  ResultAll resultAll(int status, String message, T DtoObject){
        ResultAll resultAll=new ResultAll();
        resultAll.setStatus(status);
        resultAll.setMessage(message);
        resultAll.setDtoObject(DtoObject);
         return  resultAll;
    }
}
