package ru.testing.hometasks.pom.app;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class AddHelper {


 private WebDriver driver;
 private WebDriverWait wait;

  public AddHelper(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }


  boolean isElementPresent(WebDriver driver, By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
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
