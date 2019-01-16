package cn.bin2.sport.common.mapper;

import cn.bin2.sport.common.MyMapper;
import cn.bin2.sport.common.domain.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper extends MyMapper<Admin> {
    Admin selectByUserName(@Param(value = "userName") String name);
}