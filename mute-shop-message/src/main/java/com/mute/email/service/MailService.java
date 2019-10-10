package com.mute.email.service;

import com.alibaba.fastjson.JSONObject;
import com.mute.adapter.MessageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @program: mute-shop-demo
 * @description:处理第三方发送邮件
 * @author: mute_luo
 * @create: 2019-09-24 17:41
 */
@Slf4j
@Service
public class MailService implements MessageAdapter {

    @Value("${msg.subject}")
    private String subject;
    @Value("${msg.text}")
    private String text;
    @Value("${from.email}")
    private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;
    /**
     *
     * @param body
     */
    @Override
    public void sendMsg(JSONObject body) {
        String email = body.getString("email");
        if (StringUtils.isEmpty(email)){
            return;
        }
        log.info("消息服务平台发送邮件账号："+email);
        //发邮件
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //来自账号
        simpleMailMessage.setFrom(fromEmail);
        //发送账号
        simpleMailMessage.setTo(email);
        //标题
        simpleMailMessage.setSubject(subject);
        //内容
        simpleMailMessage.setText(text.replace("{}",email));
        //发送邮件
        javaMailSender.send(simpleMailMessage);
        log.info("消息服务平台发送邮件完成.....");
    }
}
