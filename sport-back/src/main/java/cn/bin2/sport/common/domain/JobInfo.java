package cn.bin2.sport.common.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2019-03-04
 */
@TableName("JobInfo")
public class JobInfo extends Model<JobInfo> {

    private static final long serialVersionUID = 1L;

    private String job_id;

    private String job_group;

    private String job_desc;

    private String jobCron;

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }
    public String getJob_group() {
        return job_group;
    }

    public void setJob_group(String job_group) {
        this.job_group = job_group;
    }
    public String getJob_desc() {
        return job_desc;
    }

    public void setJob_desc(String job_desc) {
        this.job_desc = job_desc;
    }
    public String getJobCron() {
        return jobCron;
    }

    public void setJobCron(String jobCron) {
        this.jobCron = jobCron;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "JobInfo{" +
        "job_id=" + job_id +
        ", job_group=" + job_group +
        ", job_desc=" + job_desc +
        ", jobCron=" + jobCron +
        "}";
    }
}
