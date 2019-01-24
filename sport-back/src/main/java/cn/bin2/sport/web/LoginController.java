package cn.bin2.sport.web;

import cn.bin2.sport.common.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout){

        return "login";
    }
    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
    }
}
