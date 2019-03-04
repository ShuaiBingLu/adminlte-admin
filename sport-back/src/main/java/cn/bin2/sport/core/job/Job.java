package cn.bin2.sport.core.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 10:21 2019/3/1
 * @Modified By:
 */
@Slf4j
public class Job implements org.quartz.Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("first job running");
        log.info("first job running");
    }
}
