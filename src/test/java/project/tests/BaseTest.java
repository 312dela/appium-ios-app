package project.tests;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;

import project.helpers.ExecutionHelper;
import project.helpers.Loader;
import project.pages.PageObjectManager;
import project.utils.AssertionManager;
import project.utils.DeviceConfig;
import project.utils.DevicePool;
import project.utils.DriverManager;
import project.utils.ScreenshotOnFailureExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    private static final ThreadLocal<PageObjectManager> pageManagerTL = new ThreadLocal<>();
    private static final ThreadLocal<AssertionManager> assertionManagerTL = new ThreadLocal<>();
    private static final ThreadLocal<ExecutionHelper> executionHelperTL = new ThreadLocal<>();
    private DeviceConfig device;

    @RegisterExtension
    ScreenshotOnFailureExtension screenshot = new ScreenshotOnFailureExtension(() -> DriverManager.driver());

    @BeforeAll
    protected void initAll() {
        Loader.loadConfig();
        Loader.loadData();
        DevicePool.init();
        device = DevicePool.acquire();
    }

    @AfterAll
    protected void releaseDevice() {
        DevicePool.release(device);
    }

    @BeforeEach
    protected void setUp() throws MalformedURLException, URISyntaxException {
        DriverManager.initDriver(device);
        PageObjectManager pom = new PageObjectManager(DriverManager.driver());
        pageManagerTL.set(pom);
        executionHelperTL.set(new ExecutionHelper(pom));
        assertionManagerTL.set(new AssertionManager());
    }

    @AfterEach
    protected void tearDown() {
        if (DriverManager.driver() != null) {
            DriverManager.quitDriver();
        }
    }

    protected PageObjectManager getPage() {
        return pageManagerTL.get();
    }

    protected ExecutionHelper getExecution() {
        return executionHelperTL.get();
    }

    protected AssertionManager getAssertion() {
        return assertionManagerTL.get();
    }
}