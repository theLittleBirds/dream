package com.zc.dream.util;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;
import java.util.UUID;

/**
 *
 * Created by ThinkPad on 2019/4/11.
 */
public class UuidUtils {
    /**
     *
     * @param letter 前两位字母
     * @return 主键ID 首字母（2位）+UUID（32位）+时间戳（13位）
     */
    public static String get47UUID(String letter) {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        Date date = new Date();
        return letter+uuid+date.getTime();
    }

    public static String getUUID2(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    /**
     *
     * @param letter 前两位字母
     * @return 主键ID 首字母（2位）+UUID（32位）
     */
    public static String get34UUID(String letter) {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return letter+uuid;
    }

    /**
     * 指定头部+随机生成指定位数(16)的字符串--共18位
     * @param letter
     * @return
     */
    public static String get18RANID(String letter)
    {
        return(letter+RandomStringUtils.randomAlphanumeric(16));
    }

    public static String getUNID(String letter)
    {
        return(get18RANID(letter));
    }

    public static String getUNIDX(String letter, int num)
    {
        return(letter+RandomStringUtils.randomAlphanumeric(num));
    }

    public static String getUNIDI(String letter, int num) { return(letter+RandomStringUtils.randomNumeric(num));}

    public static void main(String[] args) {
        System.out.println(get47UUID("PF"));
        System.out.println(get34UUID("PF"));
    }
}
