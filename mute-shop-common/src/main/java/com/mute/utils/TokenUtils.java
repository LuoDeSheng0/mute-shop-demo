package com.mute.utils;

import com.mute.constants.Constants;

import java.util.UUID;

/**
 * @program: mute-shop-demo
 * @description: 生成token
 * @author: mute_luo
 * @create: 2019-09-26 10:43
 */
public class TokenUtils {
    public static String getMemberToken(){

        return Constants.TOKEN_MEMBER+"-"+ UUID.randomUUID();
    }
}
