package cn.bin2.sport.service.impl;

import cn.bin2.sport.entity.News;
import cn.bin2.sport.mapper.NewsMapper;
import cn.bin2.sport.service.INewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-03-04
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

}
