package com.gurukkalmath.monitor.service;

import com.gurukkalmath.monitor.model.MetricsData;
import com.gurukkalmath.monitor.observer.MetricsObserver;

import java.util.ArrayList;
import java.util.List;

public class SystemMonitor {
    private final List<MetricsObserver> observers=new ArrayList<>();
    private final MetricsCollector metricsCollector;

    public SystemMonitor(MetricsCollector metricsCollector) {
        this.metricsCollector = metricsCollector;
    }

    public void addObserver(MetricsObserver observer){
        observers.add(observer);
    }
    public void removeObserver(MetricsObserver observer){
        observers.remove(observer);
    }
    public void monitor(){
        MetricsData metricsData = metricsCollector.collectMetrics();
        System.out.println(observers.toString());
        notifyObsevers(metricsData);
    }
    public void notifyObsevers(MetricsData metricsData){
        for(MetricsObserver observer:observers){
            observer.updateObserver(metricsData);
        }
    }
}
