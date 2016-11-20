package ru.testing.hometasks.starts.browser.login;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestLogin extends TestBase{

  public TestLogin(String browser) {
    super(browser);
  }

  @Test
  public void testLogin() {
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }


}
