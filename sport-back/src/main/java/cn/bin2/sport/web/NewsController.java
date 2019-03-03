package cn.bin2.sport.web;

import cn.bin2.sport.common.domain.News;
import cn.bin2.sport.common.service.INewsService;
import cn.bin2.sport.core.entity.ReponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 9:08 2019/1/26
 * @Modified By:
 */
@Controller
public class NewsController {
    // TODO: 2019/1/26
    @Autowired
    private INewsService service;
    @RequestMapping(value = "new",method = RequestMethod.POST)
    @ResponseBody
    public ReponseEntity saveNews(News news){
        news.setCreate_time(LocalDateTime.now());
       boolean res = service.save(news);
       if(res)
          return ReponseEntity.success("新增成功");
       return ReponseEntity.fail();
    }
}
