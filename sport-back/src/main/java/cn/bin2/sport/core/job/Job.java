package cn.bin2.sport.core.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 10:21 2019/3/1
 * @Modified By:
 */
@Component
@EnableScheduling
@Slf4j
public class Job {

    public void firstJob(){
        log.info("first job running");
    }
}
