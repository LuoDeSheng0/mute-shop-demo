package com.mute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: mute-shop-demo
 * @description:用户实体类
 * @author: mute_luo
 * @create: 2019-09-24 14:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date created;
    private Date updated;

}
