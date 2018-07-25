package com.zhkj.util;

import org.apache.http.client.utils.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 23:41 2018/5/30 0030
 */
public class StringToDateConverter implements Converter<String,Date> {
    @Override
    public Date convert(String source) {
        return DateUtils.parseDate(source,new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"});
    }
}
