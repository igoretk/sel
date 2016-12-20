package ru.testing.hometasks.ru.pack.for19.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.testing.hometasks.ru.pack.for19.app.Application;

import java.util.Objects;

/**
 * Created by bebeka on 20.11.2016.
 */
public class TestBase {

    protected final Application app;

    public TestBase(String browser) {
        app = new Application(browser);
    }

    @BeforeClass
    public void setUp() {
        app.browser = BrowserType.CHROME;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        DesiredCapabilities caps = DesiredCapabilities.chrome();

        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);

        if (Objects.equals(app.browser, BrowserType.FIREFOX)) {
            app.driver = new FirefoxDriver();
        } else if (Objects.equals(app.browser, BrowserType.CHROME)) {
            app.driver = new ChromeDriver(caps);
        } else if (Objects.equals(app.browser, BrowserType.IE)) {
            app.driver = new InternetExplorerDriver(capabilities);
        }
        app.wait = new WebDriverWait(app.driver, 15);

        app.login("admin", "admin");

    }

    @AfterClass
    public void tearDown() {
        app.driver.quit();
        app.driver = null;

    }


    public Application getApp() {
        return app;
    }
}
