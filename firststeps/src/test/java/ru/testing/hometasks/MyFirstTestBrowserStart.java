package ru.testing.hometasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTestBrowserStart {

  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeMethod
  public void start() {
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    wait = new WebDriverWait(driver, 5);
  }
  @Test
  public void myFirstTest() {
    driver.navigate().to("http://www.google.com");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("btnG")).click();
    wait.until(titleIs("webdriver - Пошук Google"));
  }
  @AfterMethod
  public void stop() {
    driver.quit();
    driver = null;
  }

}
