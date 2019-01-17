package cn.bin2.sport.common.service.impl;

import cn.bin2.sport.common.tk.MyMapper;
import cn.bin2.sport.common.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 17:46 2019/1/16
 * @Modified By:
 */
@Service
@Transactional(readOnly = true)
public abstract class BaseServiceImpl<T, D extends MyMapper<T>> implements BaseService<T> {

    @Autowired
    private D dao;

    @Override
    @Transactional(readOnly = false)
    public int insert(T t, String createBy) { ;
        return dao.insert(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(T t) {
        return dao.delete(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(T t, String updateBy) {

        return dao.updateByPrimaryKey(t);
    }

    @Override
    public int count(T t) {
        return dao.selectCount(t);
    }

    @Override
    public T selectOne(T t) {
        return dao.selectOne(t);
    }

    @Override
    public PageInfo<T> page(int pageNum, int pageSize, T t) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNum, pageSize);

        PageInfo<T> pageInfo = new PageInfo<>(dao.select(t));
        return pageInfo;
    }
}