package com.mute.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: mute-shop-demo
 * @description:
 * @author: mute_luo
 * @create: 2019-09-20 14:10
 */
//自动生成get set toString 方法
@Data
//自动生成无参构造函数
@NoArgsConstructor
//自动生成全参数构造函数
@AllArgsConstructor
public class ResponseBase {
    private Integer code;
    private String msg;
    private Object data;

}
