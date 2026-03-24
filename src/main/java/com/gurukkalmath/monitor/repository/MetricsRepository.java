package com.gurukkalmath.monitor.repository;

import com.gurukkalmath.monitor.model.MetricsData;
import com.gurukkalmath.monitor.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MetricsRepository {
    public void save(MetricsData data){
        String sql="INSERT INTO metrics(cpu_usage,memory_usage,disk_usage,timestamp) VALUES(?,?,?,?)";
        try(Connection conn= DatabaseUtil.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql);
        ){
            System.out.println("db connected");
            stmt.setDouble(1,data.getCpuUsage());
            stmt.setDouble(2,data.getMemoryUsage());
            stmt.setDouble(3,data.getDiskUsage());
            stmt.setLong(4,data.getTimeStamp());
            int rows = stmt.executeUpdate();

            System.out.println("✅ Rows inserted: " + rows);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
