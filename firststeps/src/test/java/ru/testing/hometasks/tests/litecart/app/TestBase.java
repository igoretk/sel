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
    wait = new WebDriverWait(driver, 15);
   // driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

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

  public void clickToAddNewProduct() {
      WebElement addNewProduct = driver.findElement(By.xpath(".//*[@id='content']//a[2]"));
      addNewProduct.click();
  }

  public void catalogMenuOpen() {
      WebElement catalog = driver.findElement(By.xpath(".//*[@id='box-apps-menu']//li[2]/a"));
      catalog.click();
  }

  public void navigateToCatalogMenu() {
      driver.navigate().to("http://localhost/litecart/public_html/admin/?app=catalog&doc=catalog");
      WebElement menuCatalog = driver.findElement(By.xpath(".//li[@id='doc-catalog']/a"));
      menuCatalog.click();
  }

  public void saveButton() {
      WebElement saveButton = driver.findElement(By.xpath(".//button[@name='save']"));
      saveButton.click();
  }

  public void fillPriceTab() {
      WebElement priceTab = driver.findElement(By.xpath(".//ul[@class='index']//a[@href='#tab-prices']"));
      priceTab.click();

      WebElement purchasePrices = driver.findElement(By.xpath(".//input[@name='purchase_price']"));
      purchasePrices.clear();
      purchasePrices.sendKeys("20");

      Select priceSelect = new Select(driver.findElement(By.xpath(".//select[@name='purchase_price_currency_code']")));
      priceSelect.selectByIndex(2);

      WebElement priceUSD = driver.findElement(By.xpath(".//input[@name='prices[USD]']"));
      priceUSD.clear();
      priceUSD.sendKeys("10");

      WebElement grossUSD = driver.findElement(By.xpath(".//input[@name='gross_prices[USD]']"));
      grossUSD.clear();
      grossUSD.sendKeys("5");

      WebElement priceEUR = driver.findElement(By.xpath(".//input[@name='prices[EUR]']"));
      priceEUR.clear();
      priceEUR.sendKeys("15");

      WebElement grossEUR = driver.findElement(By.xpath(".//input[@name='gross_prices[EUR]']"));
      grossEUR.clear();
      grossEUR.sendKeys("12");
  }

  public void fillInformationTab() {
      WebElement informationTab = driver.findElement(By.xpath(".//ul[@class='index']//a[@href='#tab-information']"));
      informationTab.click();

      Select manufactured = new Select(driver.findElement(By.xpath(".//select[@name='manufacturer_id']")));
      manufactured.selectByVisibleText("ACME Corp.");

      WebElement keywords = driver.findElement(By.xpath(".//input[@name='keywords']"));
      keywords.clear();
      keywords.sendKeys("my product buy product");

      WebElement shortDescription = driver.findElement(By.xpath(".//input[@name='short_description[en]']"));
      shortDescription.clear();
      shortDescription.sendKeys("shortDescription bla bla bla");

      WebElement description = driver.findElement(By.xpath(".//div[@class='trumbowyg-editor']"));
      description.clear();
      description.sendKeys("description bla bla bla bla bla bla");

      WebElement headTitle = driver.findElement(By.xpath(".//input[@name='head_title[en]']"));
      headTitle.clear();
      headTitle.sendKeys("Head Title of a Product");

      WebElement metaDescription = driver.findElement(By.xpath(".//input[@name='meta_description[en]']"));
      metaDescription.clear();
      metaDescription.sendKeys("meta description bla bla bla bla");
  }

  public void fillGeneralTab() {
      WebElement status = driver.findElement(By.xpath("//input[@name='status' and @value='1']"));
      status.click();

      WebElement name = driver.findElement(By.xpath(".//span[@class='input-wrapper']//*[@type='text']"));
      name.clear();
      name.sendKeys("MyProduct");

      WebElement code = driver.findElement(By.xpath(".//input[@name='code']"));
      code.clear();
      code.sendKeys("123321");

      WebElement category = driver.findElement(By.xpath(".//input[@data-name='Rubber Ducks']"));
      category.click();

      WebElement gender = driver.findElement(By.xpath(".//input[@type='checkbox' and @value='1-1']"));
      gender.click();

      WebElement quantity = driver.findElement(By.xpath(".//input[@name='quantity']"));
      quantity.clear();
      quantity.sendKeys("20");

      Select quantityUnit = new Select(driver.findElement(By.xpath(".//select[@name='quantity_unit_id']")));
      quantityUnit.selectByVisibleText("pcs");

      Select deliveryStatus = new Select(driver.findElement(By.xpath(".//select[@name='delivery_status_id']")));
      deliveryStatus.selectByVisibleText("3-5 days");

      Select soldOutStatus = new Select(driver.findElement(By.xpath(".//select[@name='sold_out_status_id']")));
      soldOutStatus.selectByVisibleText("Sold out");

      WebElement upload = driver.findElement(By.xpath(".//input[@type='file']"));
      upload.sendKeys("e:\\sel\\firststeps\\Desert.jpg");

      WebElement dateValidFrom = driver.findElement(By.xpath(".//input[@name='date_valid_from']"));
      dateValidFrom.sendKeys("2016-10-11");

      WebElement dateValidTo = driver.findElement(By.xpath(".//input[@name='date_valid_to']"));
      dateValidTo.sendKeys("2016-10-12");
  }
}
