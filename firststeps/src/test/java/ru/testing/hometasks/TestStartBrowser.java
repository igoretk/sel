package ru.testing.hometasks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestStartBrowser {

  @Test
  public void testStartBrowser() {
    WebDriver driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    driver.get("http://software-testing.ru/");
    driver.quit();
  }
}
