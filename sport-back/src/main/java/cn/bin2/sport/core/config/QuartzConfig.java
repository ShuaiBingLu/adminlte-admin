package cn.bin2.sport.core.config;

import cn.bin2.sport.common.util.JobHandleExec;
import cn.bin2.sport.core.job.JobScheduler;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 10:50 2019/3/1
 * @Modified By:
 */
@Configuration
public class QuartzConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactory(DataSource dataSource){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setAutoStartup(true);
        bean.setApplicationContextSchedulerContextKey("applicationContext");

        // 覆盖已存在的任务
        bean.setOverwriteExistingJobs(true);
        // 延时启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        bean.setStartupDelay(15);
        bean.setOverwriteExistingJobs(true);
        bean.setConfigLocation(new ClassPathResource("quartz.properties"));
        return bean;
    }
    @Bean
    public JobScheduler getJobScheduler(SchedulerFactoryBean schedulerFactory){
        Scheduler scheduler =  schedulerFactory.getScheduler();
        JobScheduler jobScheduler = new JobScheduler();
        jobScheduler.setScheduler(scheduler);
        return jobScheduler;
    }
    @Bean(initMethod = "start")
    public JobHandleExec jobHandleExec(){
        JobHandleExec exec = new JobHandleExec();
        return exec;
    }
}
