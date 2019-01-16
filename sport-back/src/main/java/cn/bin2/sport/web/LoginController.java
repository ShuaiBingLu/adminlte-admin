package cn.bin2.sport.web;

import cn.bin2.sport.common.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 15:08 2019/1/15
 * @Modified By:
 */
@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;
    @GetMapping(value = "login")
    public String login(){

        return "login";
    }
}
