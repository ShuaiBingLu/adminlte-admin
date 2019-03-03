package cn.bin2.sport.core.security;

import cn.bin2.sport.common.domain.Admin;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 13:24 2019/1/25
 * @Modified By:
 */
public class SpringSecurityUserHolder implements UserInfoHolder {
    @Override
    public Admin getUser() {
        Admin admin = new Admin();
        admin.setUserName(getCurrentUserName());
        return admin;
    }

    private String getCurrentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        if (principal instanceof Principal) {
            return ((Principal) principal).getName();
        }
        return String.valueOf(principal);
    }
}
