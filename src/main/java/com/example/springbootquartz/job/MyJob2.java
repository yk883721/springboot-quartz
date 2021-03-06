package com.example.springbootquartz.job;

import com.example.springbootquartz.config.CustomCronJob;
import com.example.springbootquartz.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
@CustomCronJob(
        jobName = "job2",
        triggerName = "trigger2",
        cron = "0/5 * * * * ?"
)
public class MyJob2 extends QuartzJobBean {

    @Autowired
    private TestService testService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            String id = context.getJobDetail().getKey().getName();
            testService.run(id);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }

}
