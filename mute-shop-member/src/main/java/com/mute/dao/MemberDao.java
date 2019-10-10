package com.mute.dao;

import com.mute.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @program: mute-shop-demo
 * @description:
 * @author: mute_luo
 * @create: 2019-09-24 14:16
 */
@Mapper
public interface MemberDao {

    /**findByID
     * @param userId
     * @return
     */
    @Select("select  id,username,password,phone,email,created,updated from mb_user where id =#{userId}")
    UserEntity findByID(@Param("userId") Long userId);

    /**注册会员
     * @param userEntity
     * @return
     */
    @Insert("INSERT  INTO `mb_user`  (username,password,phone,email,created,updated) VALUES (#{username}, #{password},#{phone},#{email},#{created},#{updated});")
    Integer insertUser(UserEntity userEntity);


    /**会员登录
     * @param username
     * @param password
     * @return
     */
    @Select("select  id,username,password,phone,email,created,updated from mb_user where username =#{username} and password=#{password}")
    UserEntity login(@Param("username") String username,@Param("password") String password);

}
