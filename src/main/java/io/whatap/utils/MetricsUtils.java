package io.whatap.utils;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class MetricsUtils {
    private static OperatingSystemMXBean osBean;
    public static double getSystemCpuUsage(){
        try {
            if (osBean == null) {
                osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
                double cpuLoad = osBean.getCpuLoad();
                if (Double.isNaN(cpuLoad) || cpuLoad == 0) {
                    Thread.sleep(1000);
                }
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unable to get OperatingSystemMXBean", e);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }

        return osBean.getCpuLoad() * 100f;
    }
}
