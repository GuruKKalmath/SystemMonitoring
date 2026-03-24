package com.gurukkalmath.monitor.config;


import com.gurukkalmath.monitor.observer.AlertObserver;
import com.gurukkalmath.monitor.observer.ConsoleLogger;
import com.gurukkalmath.monitor.observer.DatabaseLogger;
import com.gurukkalmath.monitor.service.MetricsCollector;
import com.gurukkalmath.monitor.service.SystemMonitor;


public class ApplicationConfig {
    public static SystemMonitor createMonitorService(){
        MetricsCollector metricsCollector=new MetricsCollector();
        SystemMonitor systemMonitor = new SystemMonitor(metricsCollector);
        ConsoleLogger consoleLogger = new ConsoleLogger();
        systemMonitor.addObserver(consoleLogger);
        AlertObserver alertObserver = new AlertObserver();
        systemMonitor.addObserver(alertObserver);
        DatabaseLogger databaseLogger = new DatabaseLogger();
        systemMonitor.addObserver(databaseLogger);


        return systemMonitor;


    }

}
