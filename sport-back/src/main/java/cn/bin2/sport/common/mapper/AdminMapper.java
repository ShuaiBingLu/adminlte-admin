package cn.bin2.sport.common.mapper;

import cn.bin2.sport.common.domain.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;



public interface AdminMapper extends BaseMapper<Admin> {

    Admin selectByUserName(@Param(value = "userName") String userName);
}