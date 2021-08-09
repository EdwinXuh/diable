package com.ds.system.mapper;

import com.ds.common.entity.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author raptor
 * @description SysUserMapper
 * @date 2021/8/8 19:42
 */
@Mapper
public interface SysUserMapper {
    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 根据openid判断用户是否存在
     *
     * @param openId
     * @return
     */
    Integer existByOpenId(@Param("openId") String openId);

    /**
     * wechat 二次登录
     *
     * @param openId
     * @return
     */
    SysUser findByOpenId(@Param("openId") String openId);


    /**
     * 为wechat用户注册
     *
     * @param user
     */
    void insertWechatUser(SysUser user);

    /**
     * 查询某种角色全部用户
     *
     * @param coach
     * @return
     */
    List<SysUser> findAllUserByRole(@Param("coach") int coach);

    /**
     * 查找用户
     *
     * @param id
     * @return
     */
    SysUser findByUserId(@Param("id") Long id);

    /**
     * 更新用户状态
     *
     * @param id
     * @return
     */
    int updateUserStatusById(@Param("id") Long id);

    /**
     * 用户是否存在
     *
     * @param id
     * @return
     */
    Integer existById(@Param("userId") Long id);


    /**
     * 用户是否存在
     *
     * @param id
     * @return
     */
    Integer updateUserIsCoach(@Param("userId") Long id, @Param("coach") int coach);
}
