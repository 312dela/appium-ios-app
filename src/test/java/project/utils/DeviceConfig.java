package project.utils;

public record DeviceConfig(String name, String udid, String platformVersion, int wdaLocalPort, int mjpegServerPort,
                String derivedDataPath, String deviceName) {

        public static DeviceConfig fromEnv(String key) {

                String K = key.toUpperCase();

                String udid = System.getProperty("udid." + K);
                if (udid == null || udid.isBlank()) {
                        throw new IllegalStateException("-Dudid." + K + " should be defined!");
                }

                String platformVersion = System.getProperty("platformVersion." + K);
                if (platformVersion == null || platformVersion.isBlank()) {
                        throw new IllegalStateException("-DplatformVersion." + K + " should be defined!");
                }

                String deviceName = System.getProperty("deviceName." + K);
                if (deviceName == null || deviceName.isBlank()) {
                        throw new IllegalStateException("-DdeviceName." + K + " should be defined!");
                }

                int mjpeg = Integer.parseInt(System.getProperty("mjpeg." + K, "9101"));

                int wda = Integer.parseInt(System.getProperty("wda." + K, "8101"));

                String derived = System.getProperty("derived." + K, "target/WDA-" + wda);

                return new DeviceConfig("DEV_" + K, udid, platformVersion, wda, mjpeg, derived, deviceName);

        }
}