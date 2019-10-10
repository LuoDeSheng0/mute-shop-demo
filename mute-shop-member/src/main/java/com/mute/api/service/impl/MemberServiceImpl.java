package com.mute.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mute.api.service.MemberService;
import com.mute.api.service.impl.viewobject.UserVO;
import com.mute.base.BaseApiService;
import com.mute.base.BaseRedisService;
import com.mute.base.ResponseBase;
import com.mute.constants.Constants;
import com.mute.dao.MemberDao;
import com.mute.entity.UserEntity;
import com.mute.mq.RegisterMailboxProducer;
import com.mute.utils.MD5Util;
import com.mute.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mute-shop-demo
 * @description:
 * @author: mute_luo
 * @create: 2019-09-24 14:15
 */
@Slf4j
@RestController
public class MemberServiceImpl extends BaseApiService implements MemberService {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;
    @Value("${messages.queue}")
    private String MESSAGESQUEUE;



    @Override
    public ResponseBase findUserById(Long userId) {
        UserEntity userEntity = memberDao.findByID(userId);
        if (userEntity == null){
            return setResultError("该用户不存在");
        }
        return setResultSuccess(userEntity);
    }

    @Override
    public ResponseBase registerUser(@RequestBody UserEntity userEntity) {
        //参数验证
        String password = userEntity.getPassword();
        if (StringUtils.isEmpty(password)){
            return setResultError("密码不能为空");
        }
        //需要加盐
        String newPassword = MD5Util.MD5(password);
        userEntity.setPassword(newPassword);
        Integer intUser = memberDao.insertUser(userEntity);
            if (intUser <= 0){
                return setResultError("注册用户失败");
            }
            //异步发送邮件
        String email = userEntity.getEmail();
        String json = emailJson(email);
        log.info("会员服务推送消息到消息服务平台.....json"+json);
        sendMsg(json);
        return setResultSuccess("会员注册成功");
    }

    @Override
    public ResponseBase login(@RequestBody(required = false) UserEntity userEntity) {
        //1.验证参数
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();

        if (StringUtils.isEmpty(username)){
            return setResultError("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)){
            return setResultError("密码不能为空");
        }

        //2.数据库验证账号密码
        String newPwd = MD5Util.MD5(password);
        UserEntity user = memberDao.login(username, newPwd);
        if (user == null){
            return setResultError("账号或者密码不正确");
        }
        //3.对应生成token
        String memberToken = TokenUtils.getMemberToken();
        //4.存放在Redis中 key 为token value为 userID
        Integer userId = user.getId();
        log.info("用户信息token存放在Redis中...key{token}"+memberToken+"...value:{userID}"+userId);
        baseRedisService.setString(memberToken,userId+"",Constants.TOKEN_MEMBER_TIME);
        //5.返回token
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token",memberToken);

        return setResultSuccess(jsonObject);
    }

    @Override
    public ResponseBase findUserByToken(String token) {
        //验证参数
        if (StringUtils.isEmpty(token)){
            return setResultError("token不能为空");
        }
        //从Redis中 用token查询userID
        String userId =(String) baseRedisService.getString(token);
        if (StringUtils.isEmpty(userId)){
            return setResultError("token无效或已过期");
        }
        //通过userID 查找用户信息
        Long id = Long.parseLong(userId);
        UserEntity userEntity = memberDao.findByID(id);
        if (StringUtils.isEmpty(userId)) {
            return setResultError("未找到用户信息");
            }
        UserVO userVO = convertFromDataObject(userEntity);
        return setResultSuccess(userVO);
    }

    private String  emailJson(String email){

        JSONObject rootJson = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("interfaceType", Constants.SMS_MAIL);
        JSONObject content = new JSONObject();
        content.put("email",email);
        rootJson.put("header",header);
        rootJson.put("content",content);
        return rootJson.toJSONString();

    }

    private void sendMsg(String json){
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(MESSAGESQUEUE);
        registerMailboxProducer.sendMsg(activeMQQueue, json);

    }
    private UserVO convertFromDataObject(UserEntity userEntity){
        if (userEntity == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userEntity,userVO);
        return userVO;
    }
}
