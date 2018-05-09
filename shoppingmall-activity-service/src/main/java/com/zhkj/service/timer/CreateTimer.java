package com.zhkj.service.timer;

import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class CreateTimer {
    @Bean(name = "jobActivityTimer")
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(ActivityTimerCreateService activityTimerService){
        MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        methodInvokingJobDetailFactoryBean.setConcurrent(false);
        //此任务的名称
        methodInvokingJobDetailFactoryBean.setName("see_startTime");
        //此任务的分组
        methodInvokingJobDetailFactoryBean.setGroup("see_Mysql_promotionitem");
        //此任务要执行的对象
        methodInvokingJobDetailFactoryBean.setTargetObject(activityTimerService);
        //要执行对象中的那个方法
        methodInvokingJobDetailFactoryBean.setTargetMethod("execute");
        return methodInvokingJobDetailFactoryBean;
    }
    @Bean(name = "jobActivityTigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean(MethodInvokingJobDetailFactoryBean jobActivityTimer){
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobActivityTimer.getObject());
        /*
            初始时的cron表达式,每 30 秒执行一次
            格式
                秒(0-59) 分(0-59) 时(0-23) 日期(1-31) 月(1-12) 星期(1-7)
          */
        cronTriggerFactoryBean.setCronExpression("* 10 23 * * ?");
        // 触发器的名称
        cronTriggerFactoryBean.setName("activity-chhliu");
        return cronTriggerFactoryBean;
    }
    @Bean(name = "schedulerActivity")
    public SchedulerFactoryBean schedulerFactoryBean(Trigger jobActivityTigger){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //QuartzScheduler 启动时更新已存在的Job
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        //延迟 2 秒启动
        schedulerFactoryBean.setStartupDelay(2);
        //触发器
        schedulerFactoryBean.setTriggers(jobActivityTigger);
        return schedulerFactoryBean;
    }
}
