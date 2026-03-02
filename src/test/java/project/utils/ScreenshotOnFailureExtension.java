package project.utils;


import java.util.function.Supplier;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import io.appium.java_client.ios.IOSDriver;


public class ScreenshotOnFailureExtension implements AfterTestExecutionCallback {

    private Supplier<IOSDriver> driverSupplier;

    public ScreenshotOnFailureExtension(Supplier<IOSDriver> driverSupplier) {
        this.driverSupplier = driverSupplier;
    }


    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
         if (context.getExecutionException().isPresent()) {
        IOSDriver driver = driverSupplier.get();
        ScreenshotUtils.takeScreenshot(driver, "Failure screenshot - " + context.getDisplayName());
    }
    }
}