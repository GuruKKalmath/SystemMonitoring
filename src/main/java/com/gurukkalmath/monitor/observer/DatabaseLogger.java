package com.gurukkalmath.monitor.observer;

import com.gurukkalmath.monitor.model.MetricsData;
import com.gurukkalmath.monitor.repository.MetricsRepository;

public class DatabaseLogger implements MetricsObserver {
    private final MetricsRepository repository= new MetricsRepository();
    @Override
    public void updateObserver(MetricsData data){
        repository.save(data);
    }
}
