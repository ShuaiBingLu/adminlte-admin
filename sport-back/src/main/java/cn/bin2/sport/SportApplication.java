package cn.bin2.sport;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 15:03 2019/1/15
 * @Modified By:
 */
@SpringBootApplication
@MapperScan(basePackages  = "cn.bin2.sport.common.mapper")
@EnableWebSecurity
public class SportApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportApplication.class,args);
    }
}
