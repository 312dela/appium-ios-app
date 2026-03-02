package project.utils;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;

import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Allure;

public class ScreenshotUtils {

    public static void takeScreenshot(IOSDriver driver, String name) {
        if (driver == null) return;

        try {
            byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}