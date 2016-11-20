package ru.testing.hometasks.starts.browser.login;

import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by bebeka on 20.11.2016.
 */
public class TestBase {
    public WebDriver driver;
    public WebDriverWait wait;
    public String browser;

    public TestBase(String browser) {
        this.browser = browser;
    }

    @BeforeClass
    public void setUp() {
        browser = BrowserType.IE;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setBinary("c:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);

        if (Objects.equals(browser, BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (Objects.equals(browser, BrowserType.CHROME)) {
            driver = new ChromeDriver(options);
        } else if (Objects.equals(browser, BrowserType.IE)) {
            driver = new InternetExplorerDriver(capabilities);
        }

        System.out.println(((HasCapabilities) driver).getCapabilities());

        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        //  driver.manage().window().maximize();
        driver.get("http://localhost/litecart/public_html/admin");

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        driver = null;

    }
}
