package project.helpers;

import java.io.InputStream;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.models.Account;
import project.models.Address;
import project.models.Payment;
import project.models.Product;
import project.models.TestDataRoot;

public class Loader {

    private static TestDataRoot testDataRoot;
    private static Properties props = new Properties();

    public static void loadData() {

        String env = getEnvironment();
        String dataFile = "test_data_" + env + ".json";

        try (InputStream input = Loader.class.getClassLoader()
                .getResourceAsStream(dataFile)) {
            testDataRoot = new ObjectMapper().readValue(input, TestDataRoot.class);
        }

        catch (Exception e) {
            throw new RuntimeException("Failed to load test data from " + dataFile, e);
        }
    }

    public static void loadConfig() {

        String propertyFile = "config.properties";
        try (InputStream input = Loader.class.getClassLoader()
                .getResourceAsStream(propertyFile)) {
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config: " + propertyFile, e);
        }

    }

    public static String getEnvironment() {

        String env = System.getProperty("environment");

        if (env == null || env.isBlank())
            env = props.getProperty("default.env", "production");
        return env;

    }

    public static String getApp() {
        String app = System.getProperty("app");

        String env = getEnvironment();

        String configPath = props.getProperty("app." + env,
                "src/test/resources/MyDemoApp.app");

        if (app == null || app.isBlank())
            app = System.getProperty("user.dir") + "/" + configPath;
        return app;

    }

    public static String getAppiumServer() {

        String appiumServer = System.getProperty("appiumServer");

        if (appiumServer == null || appiumServer.isBlank())
            appiumServer = props.getProperty("default.appium.server", "http://127.0.0.1:4723");
        return appiumServer;
    }

    public static Product product() {

        return testDataRoot.product();

    }

    public static Account account() {

        return testDataRoot.account();

    }

    public static Address address() {

        return testDataRoot.address();

    }

    public static Payment payment() {

        return testDataRoot.payment();

    }
}
