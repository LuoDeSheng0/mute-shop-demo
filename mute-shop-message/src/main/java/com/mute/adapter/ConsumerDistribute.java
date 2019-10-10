package com.mute.adapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mute.constants.Constants;
import com.mute.email.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @program: mute-shop-demo
 * @description: 消息监听 适配器
 * @author: mute_luo
 * @create: 2019-09-24 17:26
 */
@Slf4j
@Component
public class ConsumerDistribute {
    @Autowired
    private MailService mailService;
    private MessageAdapter messageAdapter;

    /**
     * 监听消息
     * @param json
     */
    @JmsListener(destination = "messages_queue")
    public void distribute(String json) {
        log.info("####ConsumerDistribute###distribute() 消息服务平台接受 json参数:" + json);
        if (StringUtils.isEmpty(json)) {
            return;
        }
        JSONObject jsonObject = JSON.parseObject(json);
        //拿到请求头内容
        JSONObject header = jsonObject.getJSONObject("header");
        //拿到接口类型
        String interfaceType = header.getString("interfaceType");

        if (StringUtils.isEmpty(interfaceType)) {
            return;
        }
        //判断接口类型
        if (interfaceType.equals(Constants.SMS_MAIL)) {
            //调用发邮件服务
            messageAdapter = mailService;
        }
        if (messageAdapter == null) {
            return;
        }
        JSONObject body = jsonObject.getJSONObject("content");
        messageAdapter.sendMsg(body);

    }

}

