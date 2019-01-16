package cn.bin2.sport.common.service.impl;

import cn.bin2.sport.common.domain.Admin;
import cn.bin2.sport.common.mapper.AdminMapper;
import cn.bin2.sport.common.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 17:51 2019/1/16
 * @Modified By:
 */
@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<Admin, AdminMapper> implements AdminService {

}
