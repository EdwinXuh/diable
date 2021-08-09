package com.ds.common.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author raptor
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel(value = "用户实体")
public class SysUser extends BaseEntity implements UserDetails {

    @NotNull
    @ApiModelProperty(value = "用户名", example = "斗龙战士")
    private String username;

    @ApiModelProperty(value = "密码", example = "xxxx")
    private String password;

    @ApiModelProperty(value = "状态码", example = "1 正常 0 封号")
    private Integer status;

    @ApiModelProperty(value = "电话", example = "18758159060")
    private String phone;

    @ApiModelProperty(value = "wechatId", example = "xxxxxxx")
    private String openId;

    @JsonIgnoreProperties(value = {"users"})
    private List<SysRole> roles = new ArrayList<>();

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.status == 1;
    }

    public SysUser() {
    }

    public SysUser(@NotNull String username, String password, Integer status, String phone, String openId) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.phone = phone;
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                super.toString() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", openId='" + openId + '\'' +
                ", roles=" + roles +
                '}';
    }
}
