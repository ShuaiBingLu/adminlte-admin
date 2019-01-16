package cn.bin2.sport.core.service;

import cn.bin2.sport.common.domain.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 16:49 2019/1/16
 * @Modified By:
 */
public class MyUserDetail implements UserDetails {
    // 用户信息
    private Admin user;
    // 用户角色
    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetail(Admin user, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.user = user;
        this.authorities = authorities;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getUserPwd();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;

    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}