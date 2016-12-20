package ru.testing.hometasks.ru.pack.for19;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;

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

        DesiredCapabilities caps = DesiredCapabilities.chrome();

        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);

        if (Objects.equals(browser, BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (Objects.equals(browser, BrowserType.CHROME)) {
            driver = new ChromeDriver(caps);
        } else if (Objects.equals(browser, BrowserType.IE)) {
            driver = new InternetExplorerDriver(capabilities);
        }
        wait = new WebDriverWait(driver, 15);

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


    public void deleteItems() {
      List<WebElement> table = driver.findElements(By.xpath("//*[@id='order_confirmation-wrapper']//td[@class='item']"));
      for (int j = 0; j < table.size(); j++) {
        List<WebElement> tableRow = driver.findElements(By.xpath("//*[@id='order_confirmation-wrapper']//td[@class='item']"));
        WebElement removeButton = driver.findElement(By.xpath("//button[@name='remove_cart_item']"));
        removeButton.click();
        wait.until(ExpectedConditions.stalenessOf(tableRow.get(0)));
      }
    }

    public void openCart() {
      driver.navigate().to("http://localhost/litecart/public_html/en/checkout");
    }

    public void addToCart() {
      Random rand = new Random();

      driver.navigate().to("http://localhost/litecart/public_html/en/");

      for (int i = 0; i < 3; i++) {
        List<WebElement> ducks = driver.findElements(By.xpath(".//*[@class='content']//ul[@class='listing-wrapper products']//li"));
        int n = rand.nextInt(ducks.size());
        ducks.get(n).click();
        if (isElementPresent(driver, (By.xpath(".//select[@name='options[Size]']")))) {
          Select select = new Select(driver.findElement(By.xpath(".//select[@name='options[Size]']")));
          select.selectByVisibleText("Small");
        }
        WebElement buttonAddToCart = driver.findElement(By.xpath(".//button[@name='add_cart_product']"));
        WebElement count = driver.findElement(By.xpath(".//span[@class='quantity']"));
        int countBeforeAdd = Integer.parseInt(count.getAttribute("innerText"));
        buttonAddToCart.click();
        wait.until(ExpectedConditions.attributeContains(count, "innerText", Integer.toString(countBeforeAdd + 1)));
      }
    }
}
