package cn.bin2.sport.core.service;

import cn.bin2.sport.common.domain.Admin;
import cn.bin2.sport.common.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 16:53 2019/1/16
 * @Modified By:
 */
public class AuthUserDetailService implements UserDetailsService {
    @Autowired
    private AdminMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try {
            Admin user = userMapper.selectByUserName(name);
            if(user != null) {

                Collection<GrantedAuthority> authorities = new ArrayList<>();

                //封装自定义UserDetails类
                userDetails = new MyUserDetail(user, authorities);
            } else {
                throw new UsernameNotFoundException("该用户不存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDetails;
    }

}
