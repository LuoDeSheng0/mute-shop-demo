package com.mute.constants;

/**
 * @program: mute-shop-demo
 * @description: 常量类
 * @author: mute_luo
 * @create: 2019-09-20 14:22
 */
public class Constants {
    // 响应code
    public static final String HTTP_RES_CODE_NAME = "code";
    // 响应msg
    public static final   String HTTP_RES_CODE_MSG = "msg";
    // 响应data
    public static final String HTTP_RES_CODE_DATA = "data";
    // 响应请求成功
    public static final String HTTP_RES_CODE_200_VALUE = "success";
    // 系统错误
    public static final String HTTP_RES_CODE_500_VALUE = "fial";
    // 响应请求成功code
    public static final Integer HTTP_RES_CODE_200 = 200;
    // 系统错误
    public static final Integer HTTP_RES_CODE_500 = 500;

    public static final String SMS_MAIL = "email";
    //会员token
    public static final String TOKEN_MEMBER = "TOKEN_MEMBER";
    //token过期时间 用户有效期
    public static final long TOKEN_MEMBER_TIME = 60* 60*90;

}
