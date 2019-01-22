package cn.bin2.sport.web;

import cn.bin2.sport.common.domain.Admin;
import cn.bin2.sport.common.service.AdminService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 15:30 2019/1/15
 * @Modified By:
 */
@Controller
public class IndexController {

    @Autowired
    private AmqpTemplate template;
    @Autowired
    private AdminService adminService;
    @GetMapping(value = "index")
    public String index(){
        return "index";
    }

    @ResponseBody
    @GetMapping(value = "user")
    public Admin getUser(){
       return adminService.selectUser();
    }

    @GetMapping(value = "send")
    public String send(){
        template.convertAndSend("exchange","topic.message","hello rabbit");
        return "test";
    }
    @GetMapping(value = "edit")
    public String edit(){
        return "edit";
    }
}
