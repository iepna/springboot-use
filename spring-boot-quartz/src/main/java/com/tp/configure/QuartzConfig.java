package com.tp.configure;

import com.tp.service.quartz.MyCronJob;
import com.tp.service.quartz.MyJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ParseException;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * quartz������
 */
@Configuration
public class QuartzConfig {
    // ʹ��jobDetail��װjob
    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(MyJob.class).withIdentity("myJob").storeDurably().build();
    }

    // ��jobDetailע�ᵽtrigger��ȥ
    @Bean
    public Trigger myJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(15).repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity("myJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    // ʹ��jobDetail��װjob
    @Bean
    public JobDetail myCronJobDetail() {
        return JobBuilder.newJob(MyCronJob.class).withIdentity("myCronJob").storeDurably().build();
    }

    // ��jobDetailע�ᵽCron���ʽ��trigger��ȥ
    @Bean
    public Trigger CronJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");

        return TriggerBuilder.newTrigger()
                .forJob(myCronJobDetail())
                .withIdentity("myCronJobTrigger")
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
