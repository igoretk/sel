package ru.testing.hometasks.starts.browser.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
    browser = BrowserType.CHROME;
    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-maximized");
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);

    if (Objects.equals(browser, BrowserType.FIREFOX)) {
      driver = new FirefoxDriver();
    } else if (Objects.equals(browser, BrowserType.CHROME)) {
      driver = new ChromeDriver(options);
    } else if (Objects.equals(browser, BrowserType.IE)) {
      driver = new InternetExplorerDriver(capabilities);
    }
    wait = new WebDriverWait(driver, 5);
    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    login("admin", "admin");

  }

  public void login(String username, String password) {
    driver.get("http://localhost/litecart/public_html/admin");
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("login")).click();
  }

  @AfterClass
  public void tearDown() {
    driver.quit();
    driver = null;

  }

  boolean isElementPresent(WebDriver driver, By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
