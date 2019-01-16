package cn.bin2.sport.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 17:40 2019/1/16
 * @Modified By:
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
