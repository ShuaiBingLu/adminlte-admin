package cn.bin2.sport.core.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 10:50 2019/3/1
 * @Modified By:
 */
@Configuration
public class QuartzConfig {

    @Bean("schedulerFactory")
    public SchedulerFactoryBean schedulerFactory(@Qualifier("firstTrigger") Trigger firstTrigger){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(firstTrigger);
        // 覆盖已存在的任务
        bean.setOverwriteExistingJobs(true);
        // 延时启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        bean.setStartupDelay(15);
        return bean;
    }
}
