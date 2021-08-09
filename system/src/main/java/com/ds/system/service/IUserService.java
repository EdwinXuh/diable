package com.ds.system.service;

import com.ds.common.entity.domain.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * @author raptor
 * @description UserService
 * @date 2021/8/8 19:14
 */
public interface IUserService extends UserDetailsService {
    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    SysUser findByUsername(@Param("username") String username);

}
