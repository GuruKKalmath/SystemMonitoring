package com.gurukkalmath.monitor.observer;

import com.gurukkalmath.monitor.model.MetricsData;



public class AlertObserver implements MetricsObserver {
    private final double CPULimit=75.0;
    private final double MemoryLimit=75.0;
    private final double DiskLimit=75.0;
   @Override
    public void updateObserver(MetricsData metricsData){
       double curcpu=metricsData.getCpuUsage();
       double curmem=metricsData.getMemoryUsage();
       double curdis=metricsData.getDiskUsage();

       if(curcpu>CPULimit){
           System.out.println("High CPU usage:   " +curcpu );
       }

       if(curmem>MemoryLimit){
           System.out.println("High Memory usage:   " +curmem );
       }

       if(curdis>DiskLimit){
           System.out.println("High Disk usage:   " +curdis );
       }
   }
}
