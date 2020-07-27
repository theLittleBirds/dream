package com.zc.dream.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 *  Creat by zhoudi on 2020/7/14.
 */
public class SHA1Utils {

    /**
     * @Comment SHA1加密密码
     * @return
     */
    public static String encodePassword(String psw) {
        if(StringUtils.isEmpty(psw)){
            return null;
        }else{
            return DigestUtils.sha1Hex(psw);
        }
    }
}
