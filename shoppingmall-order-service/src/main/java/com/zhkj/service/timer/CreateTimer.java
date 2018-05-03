package com.zhkj.service.timer;

import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 定时任务配置类
 */
@Configuration
public class CreateTimer {
    /**
     * 要执行的任务
     * @return
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(OrderFromTimerService orderFromTimerService){
        MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        /*
            是否并发执行
                false:
                    上个任务未执行完成,下个任务等待
                true:
                    上个任务未执行完成,下个任务依旧执行
         */
        methodInvokingJobDetailFactoryBean.setConcurrent(false);
        //此任务的名称
        methodInvokingJobDetailFactoryBean.setName("see_endTime");
        //此任务的分组
        methodInvokingJobDetailFactoryBean.setGroup("see_Mysql_orderFrom");
        //此任务要执行的对象
        methodInvokingJobDetailFactoryBean.setTargetObject(orderFromTimerService);
        //要执行对象中的那个方法
        methodInvokingJobDetailFactoryBean.setTargetMethod("execute");
        return methodInvokingJobDetailFactoryBean;
    }

    /**
     *  执行任务触发的条件
     * @return
     */
    @Bean(name = "jobTigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean(MethodInvokingJobDetailFactoryBean jobDetail){
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail.getObject());
        /*
            初始时的cron表达式,每 30 秒执行一次
            格式
                秒(0-59) 分(0-59) 时(0-23) 日期(1-31) 月(1-12) 星期(1-7)
          */
        cronTriggerFactoryBean.setCronExpression("29 * * * * ?");
        // 触发器的名称
        cronTriggerFactoryBean.setName("srd-chhliu");
        return cronTriggerFactoryBean;
    }
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactoryBean(Trigger cronJobTrigger){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //QuartzScheduler 启动时更新已存在的Job
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        //延迟 1 秒启动
        schedulerFactoryBean.setStartupDelay(1);
        //触发器
        schedulerFactoryBean.setTriggers(cronJobTrigger);
        return schedulerFactoryBean;
    }
}
