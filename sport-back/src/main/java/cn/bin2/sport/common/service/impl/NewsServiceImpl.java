package cn.bin2.sport.common.service.impl;

import cn.bin2.sport.common.domain.News;
import cn.bin2.sport.common.mapper.NewsMapper;
import cn.bin2.sport.common.mapper.SiteMapper;
import cn.bin2.sport.common.service.INewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-01-25
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

}
