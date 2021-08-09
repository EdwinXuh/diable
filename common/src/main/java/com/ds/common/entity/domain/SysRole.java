package com.ds.common.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raptor
 */
@Lazy(value = false)
@ApiModel("角色实体")
public class SysRole extends BaseEntity implements GrantedAuthority {

    @ApiModelProperty(value = "角色名", example = "ROLE_ADMIN")
    private String roleName;

    @ApiModelProperty(value = "角色描述", example = "ROLE_ADMIN")
    private String roleDesc;

    @JsonIgnoreProperties(value = {"roles"})
    private List<SysUser> users = new ArrayList<>();


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }


    public List<SysUser> getUsers() {
        return users;
    }

    public void setUsers(List<SysUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                super.toString() +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
