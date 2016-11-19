package ru.testing.hometasks;

import org.openqa.selenium.By;
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
import org.testng.annotations.Test;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TestLogin {

  private WebDriver driver;
  private WebDriverWait wait;
  private String browser;
  public TestLogin(String browser) {
    this.browser = browser;
  }
  @BeforeClass
  public void setUp() {

    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-fullscreen");
    DesiredCapabilities cap = new DesiredCapabilities();
    cap.setCapability(ChromeOptions.CAPABILITY, options);
    browser = BrowserType.CHROME;
    if (Objects.equals(browser, BrowserType.FIREFOX)) {
      driver = new FirefoxDriver(cap);
    } else if (Objects.equals(browser, BrowserType.CHROME)) {
      driver = new ChromeDriver(cap);
    } else if (Objects.equals(browser, BrowserType.IE)) {
      driver = new InternetExplorerDriver(cap);
    }

    System.out.println(((HasCapabilities) driver).getCapabilities());

    wait = new WebDriverWait(driver, 5);
    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
  //  driver.manage().window().maximize();
    driver.get("http://localhost/litecart/public_html/admin");

  }

  @Test
  public void testLogin() {
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }

  @AfterClass
  public void tearDown() {
    driver.quit();
    driver = null;

  }
}
