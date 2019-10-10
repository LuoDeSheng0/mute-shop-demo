package com.mute.base;

import com.mute.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: mute-shop-demo
 * @description:
 * @author: mute_luo
 * @create: 2019-09-20 14:15
 */
@Component
public class BaseApiService {
    @Autowired
    protected BaseRedisService baseRedisService;


    public ResponseBase setResultError(String msg){
        return setResult(Constants.HTTP_RES_CODE_500,msg,null);
    }
    /**
     * 返回成功有data值
     * @param data
     * @return
     */
    public ResponseBase setResultSuccess(Object data){
        return setResult(Constants.HTTP_RES_CODE_200,Constants.HTTP_RES_CODE_200_VALUE,data);
    }
    /**
     * 返回成功 但没有data值
     * @return
     */
    public ResponseBase setResultSuccess(){
        return setResult(Constants.HTTP_RES_CODE_200,Constants.HTTP_RES_CODE_200_VALUE,null);
    }
    /**
     * 返回成功 但没有data值
     * @return
     */
    public ResponseBase setResultSuccess(String msg){
        return setResult(Constants.HTTP_RES_CODE_200,msg,null);
    }
    /**
     * 通用封装
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public ResponseBase setResult(Integer code,String msg,Object data){

        return new ResponseBase(code,msg,data);
    }


}
