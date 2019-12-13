package com.fintech.modules.boot.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Description
 * <br>  定义日期处理工具类
 *
 * @author chendongdong
 * @date 2019/12/10
 **/
@Slf4j
public class DateUtil {

    private static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * Description
     * <br>  字符串转化成日期
     *
     * @param dateStr 日期字符串
     * @date 2019/09/20
     * @author chendongdong
     **/
    public static Date fromStingToDate(String dateStr) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            log.error("字符串转化为日期异常", e);
        }
        return date;
    }

    private DateUtil() {
    }
}
