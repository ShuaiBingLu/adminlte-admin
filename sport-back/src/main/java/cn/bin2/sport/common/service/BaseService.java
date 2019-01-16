package cn.bin2.sport.common.service;

import com.github.pagehelper.PageInfo;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 17:42 2019/1/16
 * @Modified By:
 */
public interface BaseService<T> {
    int insert(T t, String createBy);

    int delete(T t);

    int update(T t, String updateBy);

    int count(T t);

    T selectOne(T t);

    PageInfo<T> page(int pageNum, int pageSize, T t);
}
