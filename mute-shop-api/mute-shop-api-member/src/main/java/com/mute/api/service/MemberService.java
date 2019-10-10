package com.mute.api.service;

import com.mute.base.ResponseBase;
import com.mute.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: mute-shop-demo
 * @description:
 * @author: mute_luo
 * @create: 2019-09-24 14:14
 */
@RequestMapping("/member")
public interface MemberService {
    /**
     * find user by userId
     * @param userId
     * @return
     */
    @RequestMapping("/findUserById")
    ResponseBase findUserById(Long userId);
    @RequestMapping("/registerUser")
    ResponseBase registerUser(@RequestBody UserEntity userEntity);
    @RequestMapping("/login")
    ResponseBase login(@RequestBody UserEntity userEntity);

    //用token查询用户信息

    @RequestMapping("/findUserByToken")
    ResponseBase findUserByToken(String token);


}
