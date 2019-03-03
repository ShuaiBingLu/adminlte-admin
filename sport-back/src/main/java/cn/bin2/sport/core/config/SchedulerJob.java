package cn.bin2.sport.core.config;

import cn.bin2.sport.core.job.Job;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 10:14 2019/3/1
 * @Modified By:
 */
@Configuration
public class SchedulerJob {
    @Bean(name = "firstJob")
    public MethodInvokingJobDetailFactoryBean firstJob(Job job){
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setConcurrent(false);
        bean.setName("firstJob");
        bean.setGroup("firstGroup");
        bean.setTargetObject(Job.class);
        bean.setTargetMethod("firstJob");
        return bean;
    }
    @Bean(name="firstTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean(@Qualifier("firstJob") MethodInvokingJobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression("0/10 * * * * ?");
        bean.setJobDetail(jobDetailFactoryBean.getObject());
        bean.setName("firstTrigger");
        return bean;
    }


}
