package com.ds.system.mapper;

import com.ds.common.entity.domain.SysRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author raptor
 * @description SysRoleMapper
 * @date 2021/8/8 19:45
 */
@Mapper
public interface SysRoleMapper {
    @Select("SELECT r.id, r.role_name roleName, r.role_desc roleDesc "
            + "FROM sys_role r, sys_user_role ur "
            + "WHERE r.id=ur.rid AND ur.uid=#{uid}")
    List<SysRole> findByUid(Integer uid);

    @Select("select id from sys_role where role_name = #{roleName}")
    Integer findRoleByName(String roleName);

    /**
     * wechat用户角色分配
     *
     * @param userId
     * @param roleId
     * @return
     */
    @Insert("insert into sys_user_role(uid,rid) values(#{userId},#{roleId})")
    Integer insertWechatRole(int userId, int roleId);
}
