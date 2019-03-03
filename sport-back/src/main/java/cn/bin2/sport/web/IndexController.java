package cn.bin2.sport.web;

import cn.bin2.sport.common.domain.Admin;
import cn.bin2.sport.common.domain.Site;
import cn.bin2.sport.common.service.AdminService;
import cn.bin2.sport.common.service.SiteService;
import cn.bin2.sport.core.security.UserInfoHolder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 15:30 2019/1/15
 * @Modified By:
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    private AmqpTemplate template;
    @Autowired
    private AdminService adminService;
    @Autowired
    private SiteService siteService;

    @Autowired
    private UserInfoHolder userInfoHolder;
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
    @GetMapping(value = "test")
    public void test(HttpServletRequest request){
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        while(attributeNames.hasMoreElements()){
            String attr = attributeNames.nextElement();

            log.info(request.getSession().getAttribute(attr).toString());
        }
    }
    @GetMapping(value = "siteInfo")
    public ModelAndView siteInfo(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("siteInfo");
        IPage page = new Page();
        page.setCurrent(1);
        page.setSize(10);
        page =  siteService.getPageSite(page,userInfoHolder.getUser().getUserName());
        List<Site> records = page.getRecords();
        modelAndView.addObject("sites",records);
        return modelAndView;
    }
}
