package com.ruoyi.system.utils;

import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @Author even
 * @Describe
 * @Time 2021/10/21 14:56:31
 */
public class IStringUtils {


    /**
     * 字符串转数组
     * @param str
     * @return
     */
    public static List<String> getMethod(String str,String s){
        if (StringUtils.hasText(str)) {
            if (str.contains(s)) {
                return Arrays.asList(str.split(s));
            }
        }
        return null;
    }


    /**
     * 转换字节大小
     *
     * @param size
     * @return
     */
    public static String getSize(long size) {
        DecimalFormat df = new DecimalFormat("0.00");
        String r;
        int GB = 1024 * 1024 * 1024;
        int MB = 1024 * 1024;
        int KB = 1024;
        if (size / GB >= 1) {
            r = df.format(size / (float) GB) + "GB";
        } else if (size / MB >= 1) {
            r = df.format(size / (float) MB) + "MB";
        } else if (size / KB >= 1) {
            r = df.format(size / (float) KB) + "KB";
        } else {
            r = size + "B";
        }
        return r;
    }
}
