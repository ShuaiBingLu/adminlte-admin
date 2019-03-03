package cn.bin2.sport.common.service;

import cn.bin2.sport.common.domain.Site;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 14:00 2019/1/25
 * @Modified By:
 */
public interface SiteService {

    IPage<Site> getPageSite(IPage<Site> siteIPage, String userName);
}
