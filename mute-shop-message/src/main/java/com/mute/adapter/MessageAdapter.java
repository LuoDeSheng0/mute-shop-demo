package com.mute.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 * @program: mute-shop-demo
 * @description:统一发送消息接口
 * @author: mute_luo
 * @create: 2019-09-24 17:23
 */
public interface MessageAdapter {

    public void sendMsg(JSONObject body);
}
