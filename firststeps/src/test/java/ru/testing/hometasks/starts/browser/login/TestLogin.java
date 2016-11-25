package ru.testing.hometasks.starts.browser.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;
import static org.testng.Assert.assertTrue;

public class TestLogin extends TestBase {

  public TestLogin(String browser) {
    super(browser);
  }


  @Test
  public void testClick() throws InterruptedException {
    List<WebElement> menuItem = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));
    //System.out.println(menuItem.size());
    for (int i = 1; i < menuItem.size(); i++) {

      WebElement item = driver.findElement(By.xpath(String.format("//ul[@id='box-apps-menu']/li[%s]", i)));
      item.click();
      assertTrue(isElementPresent(driver, By.tagName("h1")));

      List<WebElement> subMenuItem = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li/ul/li"));
      // System.out.println(subMenuItem.size());
      if (subMenuItem.size() > 0) {
        for (int j = 1; j < subMenuItem.size() + 1; j++) {

          WebElement subItem = driver.findElement(By.xpath(String.format("//ul[@id='box-apps-menu']/li/ul/li[%s]", j)));
          subItem.click();
          assertTrue(isElementPresent(driver, By.tagName("h1")));

        }
      }
    }

  }

  @Test
  public void testStickers() {
    driver.navigate().to("http://localhost/litecart/public_html");
    List<WebElement> product = driver.findElements(By.xpath(".//*[@id='box-most-popular']/div/ul/li"));
    System.out.println(product.size());
    for (int i = 1; i < product.size(); i++) {
     // System.out.println(product.get(i).getText());
      
      assertTrue(isElementPresent(driver, By.cssSelector("class~=sticker")));

    }
  }
}







