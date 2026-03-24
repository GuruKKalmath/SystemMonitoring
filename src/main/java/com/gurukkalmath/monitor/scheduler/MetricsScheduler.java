package com.gurukkalmath.monitor.scheduler;

import com.gurukkalmath.monitor.service.MetricsCollector;
import com.gurukkalmath.monitor.service.SystemMonitor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MetricsScheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

public void start(SystemMonitor systemMonitor){
    Runnable task=new Runnable()
    {
        @Override
        public void run () {
            systemMonitor.monitor();
    }
    };
    scheduler.scheduleAtFixedRate(task,0,2, TimeUnit.SECONDS);



}
}