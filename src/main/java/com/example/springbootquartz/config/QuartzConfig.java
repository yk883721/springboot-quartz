package com.example.springbootquartz.config;

import com.example.springbootquartz.job.MyJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class QuartzConfig {

    @Autowired
    private Scheduler scheduler;

//    @PostConstruct
    public void test() throws SchedulerException {

        JobDetail jobDetail = JobBuilder
                .newJob(MyJob.class)
                .withIdentity("myJob1", "group1")
                .storeDurably()
                .build();

        SimpleTrigger trigger = TriggerBuilder
                .newTrigger()
                .startNow()
                .withIdentity("trigger1", "group1")
                .forJob(jobDetail)
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(5)
                                .repeatForever()
                )
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

    }

}
