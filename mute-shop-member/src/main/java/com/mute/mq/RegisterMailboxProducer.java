package com.mute.mq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @program: mute-shop-demo
 * @description:
 * @author: mute_luo
 * @create: 2019-09-24 18:01
 */
@Service
public class RegisterMailboxProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(Destination destination, String json){
        jmsMessagingTemplate.convertAndSend((Destination) destination,json);
    }
}
