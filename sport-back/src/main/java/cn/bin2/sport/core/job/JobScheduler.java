package cn.bin2.sport.core.job;


import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.Job;
import org.quartz.spi.MutableTrigger;

import java.util.Date;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 8:49 2019/3/4
 * @Modified By:
 */
@Slf4j
public final class JobScheduler {


    private static Scheduler  scheduler;

    public void setScheduler(Scheduler scheduler){
        JobScheduler.scheduler = scheduler;
    }

    public static boolean addJob(String jobName,String group,String cronExpression) throws SchedulerException{
        TriggerKey triggerKey = new TriggerKey(jobName,group);
        if(scheduler.checkExists(triggerKey))
            return  true;
        JobKey jobKey = new JobKey(jobName,group);
        CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(builder).build();
        Class<? extends Job> jobClass_ = cn.bin2.sport.core.job.Job.class;
        JobDetail jobDetail = JobBuilder.newJob(jobClass_).withIdentity(jobKey).build();

        Date date = scheduler.scheduleJob(jobDetail,trigger);
        log.info("success {}",date);
        return true;
    }

    public static boolean removeJob(String jobName,String group) throws SchedulerException{
        TriggerKey triggerKey = new TriggerKey(jobName,group);
        if(scheduler.checkExists(triggerKey)){
            scheduler.unscheduleJob(triggerKey);
        }
        return true;
    }

    public static boolean updateCron(String jobName,String group,String cronExpression) throws SchedulerException{
        TriggerKey triggerKey = new TriggerKey(jobName,group);
        if(!scheduler.checkExists(triggerKey)){
            return true;
        }
        CronTrigger oldTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);
        if(oldTrigger.getCronExpression().equals(cronExpression))
            return true;
        CronScheduleBuilder build = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
        oldTrigger = oldTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(build).build();
        scheduler.rescheduleJob(triggerKey,oldTrigger);

        return true;

    }

}
