package com.mute.api.service.impl;

import com.mute.api.service.TestApiService;
import com.mute.base.BaseApiService;
import com.mute.base.ResponseBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: mute-shop-demo
 * @description:
 * @author: mute_luo
 * @create: 2019-09-20 10:27
 */
@Slf4j
@RestController
public class TestApiServiceImpl extends BaseApiService implements TestApiService {


    @Override
    public Map<String, Object> test(Integer id, String name) {
        HashMap<String, Object> resultMap = new HashMap<>(16);
        resultMap.put("code","200");
        resultMap.put("msg","success");
        resultMap.put("data",new String[]{"123","456"});

        return resultMap;
    }

    @Override
    public ResponseBase testResponseBase() {
        return setResultSuccess();
    }

    @Override
    public ResponseBase testRedis(String key, String value) {
         baseRedisService.setString(key,value,null);
        return setResultSuccess() ;
    }


}
