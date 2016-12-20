package ru.testing.hometasks.pom.app;

import org.openqa.selenium.WebDriver;

public class OpenHelper {
  private WebDriver driver;


  public OpenHelper(WebDriver driver) {
    this.driver = driver;
  }

  public void openCart() {
    driver.navigate().to("http://localhost/litecart/public_html/en/checkout");
  }
}
