package com.gurukkalmath.monitor.service;

import com.gurukkalmath.monitor.model.MetricsData;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;


public class MetricsCollector {

    private final SystemInfo systemInfo = new SystemInfo();
    private final CentralProcessor processor = systemInfo.getHardware().getProcessor();
    private final GlobalMemory memory = systemInfo.getHardware().getMemory();
    private final FileSystem fileSystem=  systemInfo.getOperatingSystem().getFileSystem();


    private long[] prevTicks = processor.getSystemCpuLoadTicks();

    public MetricsData collectMetrics() {

        double cpuUsage = getCpuUsage();
        double memoryUsage = getMemoryUsage();
        double diskUsage = getDiskUsage();

        long timestamp = System.currentTimeMillis();

        return new MetricsData(cpuUsage, memoryUsage, diskUsage, timestamp);
    }

    private double getCpuUsage() {
        double load = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
        prevTicks = processor.getSystemCpuLoadTicks();
        return load;
    }

    private double getMemoryUsage() {
        long total = memory.getTotal();
        long available = memory.getAvailable();

        return ((double) (total - available) / total) * 100;
    }
    private  double getDiskUsage(){
        long total=0;
        long usage= 0;
        for (OSFileStore store:fileSystem.getFileStores()) {
           total+=store.getTotalSpace();
           usage+=store.getUsableSpace();
        }
        if(total==0) return 0;
        return ((double) (total-usage)/total)*100;
    }
}