package com.example.springbootquartz.config;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

@Configuration
public class QuartzConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Scheduler scheduler;


    @PostConstruct
    public void test() throws SchedulerException {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(CustomCronJob.class);


        for (Map.Entry<String, Object> entry : beans.entrySet()) {

            Object instance = entry.getValue();

            CustomCronJob annotation = instance.getClass().getAnnotation(CustomCronJob.class);

            String jobName = annotation.jobName();
            String triggerName = annotation.triggerName();
            String cron = annotation.cron();


            JobDetail jobDetail = JobBuilder
                    .newJob((Class<? extends Job>) instance.getClass())
                    .withIdentity(jobName)
                    .storeDurably()
                    .build();

            CronTrigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerName)
                    .forJob(jobDetail)
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule(cron)
                    )
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        }
    }

}
