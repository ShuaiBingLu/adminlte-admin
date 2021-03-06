package cn.bin2.sport.core.security;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 16:44 2019/1/16
 * @Modified By:
 */
@Service("securityProvider")
public class SecurityProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;
    public SecurityProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Override
    public Authentication authenticate(Authentication authenticate) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token
                = (UsernamePasswordAuthenticationToken) authenticate;
        String username = token.getName();
        UserDetails userDetails = null;

        if(username !=null) {
            userDetails = userDetailsService.loadUserByUsername(username);
        }
        System.out.println("$$"+userDetails);

        if(userDetails == null) {
            throw new UsernameNotFoundException("用户名/密码无效");
        }
        String password = userDetails.getPassword();
        //与authentication里面的credentials相比较
        if(!password.equals(token.getCredentials())) {
            throw new BadCredentialsException("Invalid username/password");
        }
        //授权
        return new UsernamePasswordAuthenticationToken(userDetails, password,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
