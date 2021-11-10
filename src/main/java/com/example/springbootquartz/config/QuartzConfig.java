package com.example.springbootquartz.config;

import com.example.springbootquartz.job.MyJob1;
import com.example.springbootquartz.job.MyJob2;
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

        JobDetail jobDetail1 = JobBuilder
                .newJob(MyJob1.class)
                .withIdentity("myJob1", "group1")
                .storeDurably()
                .build();

        SimpleTrigger trigger1 = TriggerBuilder
                .newTrigger()
                .startNow()
                .withIdentity("trigger1", "group1")
                .forJob(jobDetail1)
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(5)
                                .repeatForever()
                )
                .build();

        JobDetail jobDetail2 = JobBuilder
                .newJob(MyJob2.class)
                .withIdentity("myJob2", "group1")
                .storeDurably()
                .build();

        SimpleTrigger trigger2 = TriggerBuilder
                .newTrigger()
                .startNow()
                .withIdentity("trigger2", "group1")
                .forJob(jobDetail2)
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(5)
                                .repeatForever()
                )
                .build();

        scheduler.scheduleJob(jobDetail1, trigger1);
        scheduler.scheduleJob(jobDetail2, trigger2);

    }

}
