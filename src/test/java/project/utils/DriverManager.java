package project.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import project.helpers.Loader;

public class DriverManager {

    private static final ThreadLocal<IOSDriver> driverTL = new ThreadLocal<>();

    public static void initDriver(DeviceConfig device) throws MalformedURLException, URISyntaxException {

        if (driver() != null) {
            quitDriver();
        }

        String appPath = Loader.getApp();
        String server = Loader.getAppiumServer();

        XCUITestOptions options = new XCUITestOptions();
        options.setApp(appPath);
        options.setAutomationName("XCUITest");
        options.setDeviceName(device.deviceName());
        options.setUdid(device.udid());
        options.setPlatformVersion(device.platformVersion());
        options.setWdaLocalPort(device.wdaLocalPort());
        options.setMjpegServerPort(device.mjpegServerPort());
        options.setDerivedDataPath(device.derivedDataPath());
        options.setWdaLaunchTimeout(Duration.ofSeconds(60));
        options.setWdaStartupRetries(2);
        options.setWdaStartupRetryInterval(Duration.ofSeconds(5));
        options.setShowXcodeLog(true);
        options.setUseNewWDA(false);
        options.setSimulatorStartupTimeout(Duration.ofSeconds(180));

        IOSDriver ios = new IOSDriver(new URI(server).toURL(), options);
        ios.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driverTL.set(ios);
    }

    public static IOSDriver driver() {
        return driverTL.get();
    }

    public static void quitDriver() {
        IOSDriver driver = driver();

        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Failed to quit driver: " + e.getMessage());
        } finally {
            driverTL.remove();
        }
    }
}