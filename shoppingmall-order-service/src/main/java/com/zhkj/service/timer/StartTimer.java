package com.zhkj.service.timer;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 编译类时启动定时任务
 */
@Component
public class StartTimer implements CommandLineRunner {
    @Autowired
    private CreateTimer createTimer;
    @Override
    public void run(String... args) throws Exception {

    }
}
