package com.gurukkalmath.monitor.observer;

import com.gurukkalmath.monitor.model.MetricsData;
import com.gurukkalmath.monitor.service.SystemMonitor;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements MetricsObserver{
    @Override
    public void updateObserver(MetricsData metricsData) {
        try (FileWriter writer=new FileWriter("SystemMetrics.log",true)) {
            writer.write(metricsData.toString()+ System.lineSeparator());
        }
        catch (IOException e){
            System.out.println("Error in handling file:  "+e.getMessage());

        }
    }




}
