package cn.bin2.sport.core.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 14:05 2019/1/25
 * @Modified By:
 */
@EnableTransactionManagement
@Configuration
@MapperScan("cn.bin2.sport.common.mapper.*")
public class MyBatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
