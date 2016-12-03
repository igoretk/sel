package ru.testing.hometasks.tests.litecart.app;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
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

  public void userLogout() {
      WebElement logOut = driver.findElement(By.xpath(".//*[@id='box-account']//li[4]/a"));
      logOut.click();
  }

  public String createAcc() {
      driver.navigate().to("http://localhost/litecart/public_html/en/");
      driver.findElement(By.xpath(".//*[@id='box-account-login']//tr[5]/td/a")).click();

      WebElement taxId = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[1]//td[1]/input"));
      taxId.clear();
      taxId.sendKeys("111222333");

      WebElement company = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[1]//td[2]/input"));
      company.clear();
      company.sendKeys("test_Company");

      WebElement firstName = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[2]//td[1]/input"));
      firstName.clear();
      firstName.sendKeys("Vasisualiy");

      WebElement lastName = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[2]//td[2]/input"));
      lastName.clear();
      lastName.sendKeys("Lohankin");

      WebElement adr1 = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[3]//td[1]/input"));
      adr1.clear();
      adr1.sendKeys("test_Address 1");

      WebElement adr2 = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[3]//td[2]/input"));
      adr2.clear();
      adr2.sendKeys("test_Address 2");

      WebElement postCode = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[4]//td[1]/input"));
      postCode.clear();
      postCode.sendKeys("61174");

      WebElement city = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[4]//td[2]/input"));
      city.clear();
      city.sendKeys("61174");

      WebElement countryToSelect = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[5]//td[1]/select"));
      Select selectCountry = new Select(countryToSelect);
      selectCountry.selectByVisibleText("United States");

      Select selectState = new Select(driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[5]//td[2]/select")));
      selectState.selectByVisibleText("Alabama");

      WebElement email = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[6]//td[1]/input"));
      email.clear();
      String emailData = "e" + java.util.UUID.randomUUID().toString().substring(0, 7) + "@mail.com";
      email.sendKeys(emailData);

      WebElement phone = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[6]//td[2]/input"));
      phone.clear();
      phone.sendKeys("+38050654321");

      WebElement desiredPsw = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[8]//td[1]/input"));
      desiredPsw.clear();
      desiredPsw.sendKeys("passwordQwe123");

      WebElement confirmPsw = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[8]//td[2]/input"));
      confirmPsw.clear();
      confirmPsw.sendKeys("passwordQwe123");

      WebElement buttonCreateAcc = driver.findElement(By.xpath(".//*[@id='create-account']//table//tr[9]//td[1]/button"));
      buttonCreateAcc.click();
      return emailData;
  }

  public void newUserLogin(String emailData) {
      WebElement emailAdr = driver.findElement(By.xpath(".//*[@name ='email']"));
      emailAdr.clear();
      emailAdr.sendKeys(emailData);

      WebElement password = driver.findElement(By.xpath(".//*[@name ='password']"));
      password.clear();
      password.sendKeys("passwordQwe123");

      WebElement btnLogin = driver.findElement(By.xpath(".//*[@name='login']"));
      btnLogin.click();
  }
}
