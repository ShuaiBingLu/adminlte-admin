package cn.bin2.sport.common.service.impl;

import cn.bin2.sport.common.domain.Admin;
import cn.bin2.sport.common.domain.Site;
import cn.bin2.sport.common.mapper.SiteMapper;
import cn.bin2.sport.common.service.SiteService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 13:58 2019/1/25
 * @Modified By:
 */
@Service
@Transactional(readOnly = true)
public class SiteServiceImpl extends ServiceImpl<SiteMapper,Site> implements SiteService {

    @Autowired
    private SiteMapper mapper;
    @Override
    public IPage<Site> getPageSite(IPage<Site> siteIPage,String userName) {
      return  mapper.selectPage(siteIPage, Wrappers.<Site>lambdaQuery().eq(Site::getCreateUser,userName));
    }
}
