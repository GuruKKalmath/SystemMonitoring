package com.gurukkalmath.monitor.model;


public class MetricsData {
    private double cpuUsage;

    private double memoryUsage;
    private double diskUsage;
    private long timeStamp;

    public MetricsData(double cpuUsage, double memoryUsage, double diskUsage, long timeStamp) {
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.diskUsage = diskUsage;
        this.timeStamp = timeStamp;
    }
    public double getCpuUsage() {
        return cpuUsage;
    }
    public double getMemoryUsage() {
        return memoryUsage;
    }
    public double getDiskUsage() {
        return diskUsage;
    }
    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return String.format(
                "+-----------------+-----------------+%n" +
                        "| Metric          | Value           |%n" +
                        "+-----------------+-----------------+%n" +
                        "| CPU Usage       | %-15s |%n" +
                        "| Memory Usage    | %-15s |%n" +
                        "| Disk Usage      | %-15s |%n" +
                        "| Timestamp       | %-15s |%n" +
                        "+-----------------+-----------------+",
                cpuUsage, memoryUsage, diskUsage, timeStamp
        );
    }
}
