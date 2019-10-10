package com.mute.api.service;

import com.mute.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @program: mute-shop-demo
 * @description: 测试接口
 * @author: mute_luo
 * @create: 2019-09-20 10:12
 */

@RequestMapping("/test")
public interface TestApiService {

    @RequestMapping("/test")
    public Map<String,Object>test(Integer id,String name);
    @RequestMapping("/testResponseBase")
    public ResponseBase testResponseBase();

    @RequestMapping("/testRedis")
    public ResponseBase testRedis(String key,String value);
}
