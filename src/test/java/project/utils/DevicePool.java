package project.utils;

import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class DevicePool {

    private static BlockingQueue<DeviceConfig> queue;

    public static synchronized void init() {
        if (queue != null)
            return;

        queue = new LinkedBlockingQueue<>();

        Properties props = System.getProperties();

        for (String key : props.stringPropertyNames()) {
            if (key.toLowerCase().startsWith("udid.")) {
                String deviceKey = key.substring(5).toUpperCase();
                addIfPresent(deviceKey);
            }
        }
        
        if (queue.isEmpty()) {
            throw new IllegalStateException("No devices found. Define -Dudid.A=...");
        }
    }

    private static void addIfPresent(String key) {
        String K = key.toUpperCase();
        String udid = System.getProperty("udid." + K);

        if (udid != null && !udid.isBlank()) {
            queue.add(DeviceConfig.fromEnv(K));
        }
    }

    public static DeviceConfig acquire() {
        if (queue == null)
            throw new IllegalStateException("DevicePool.init() not called");
        try {
            return queue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted acquiring device", e);
        }
    }

    public static void release(DeviceConfig cfg) {
        if (cfg != null && queue != null)
            queue.offer(cfg);
    }

}
