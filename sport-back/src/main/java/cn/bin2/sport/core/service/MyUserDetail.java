package cn.bin2.sport.core.service;

import cn.bin2.sport.common.domain.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 16:49 2019/1/16
 * @Modified By:
 */
public class MyUserDetail extends Admin implements UserDetails {


    public MyUserDetail(Admin user) {

        this.setUserName(user.getUserName());
        this.setUserPwd(user.getUserPwd());
        this.setNickName(user.getNickName());
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String username = this.getUsername();
        if (username != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(username);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.getUserPwd();
    }

    @Override
    public String getUsername() {
        return this.getUserName();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;

    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}