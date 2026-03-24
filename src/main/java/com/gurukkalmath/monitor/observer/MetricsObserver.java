package com.gurukkalmath.monitor.observer;

import com.gurukkalmath.monitor.model.MetricsData;

public interface MetricsObserver {
    public void updateObserver(MetricsData metricsData);

}
