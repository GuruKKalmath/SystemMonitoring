package com.gurukkalmath.monitor.observer;

import com.gurukkalmath.monitor.model.MetricsData;

public class ConsoleLogger implements com.gurukkalmath.monitor.observer.MetricsObserver {
    @Override
    public void updateObserver(MetricsData metricsData) {
        System.out.println("---------------------------Updated Metrics Data in  Console Logger--------------------------");
        System.out.println("logging data :"+ metricsData);
        //System.out.println("============================================================================================");
    }
}
