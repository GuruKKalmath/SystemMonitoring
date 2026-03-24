package com.gurukkalmath.monitor;

import com.gurukkalmath.monitor.config.ApplicationConfig;
import com.gurukkalmath.monitor.scheduler.MetricsScheduler;
import com.gurukkalmath.monitor.service.SystemMonitor;

public class Main {
    public static void main(String[] args) throws InterruptedException {



        SystemMonitor monitorService= ApplicationConfig.createMonitorService();
        MetricsScheduler scheduler = new MetricsScheduler();
        scheduler.start(monitorService);

        System.out.println("System Started .....");

    }
}
