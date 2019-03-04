package cn.bin2.sport.web;

import cn.bin2.sport.common.domain.JobInfo;
import cn.bin2.sport.common.mapper.JobInfoMapper;
import cn.bin2.sport.core.entity.ReponseEntity;
import cn.bin2.sport.core.job.JobScheduler;
import cn.bin2.sport.service.IJobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 17:13 2019/3/4
 * @Modified By:
 */
@Controller
public class JobController {

    @Autowired
    private IJobInfoService jobInfoService;
    @RequestMapping(value = "job",method = RequestMethod.POST)
    public ReponseEntity addJob(JobInfo jobInfo){
        String jobId = jobInfo.getJob_id();
        String jobGroup = jobInfo.getJob_group();
        String jobCron = jobInfo.getJobCron();
        jobInfoService.save(jobInfo);
        return ReponseEntity.success("success");
    }
    public ReponseEntity startJob(String id) throws Exception{

        JobInfo jobInfo = jobInfoService.getById(id);
        String jobId = jobInfo.getJob_id();
        String jobGroup = jobInfo.getJob_group();
        String jobCron = jobInfo.getJobCron();
        JobScheduler.addJob(jobId,jobGroup,jobCron);
        return ReponseEntity.success("success");
    }


}
