package com.tz.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    public static <T>  T copyParamsToBean(Map value, T bean) {
        try {
            // 通过 bean 的 setter 去注入同名 key 的 value 值
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String val, Integer defaultVal) {
        try {
            return Integer.parseInt(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultVal;
    }
}
