package ru.testing.hometasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestLogin {

  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeClass
  public void setUp() {
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, 5);
    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    driver.manage().window().maximize();
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
