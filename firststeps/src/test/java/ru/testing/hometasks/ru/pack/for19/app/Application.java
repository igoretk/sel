package ru.testing.hometasks.ru.pack.for19.app;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class Application {
  public WebDriver driver;
  public WebDriverWait wait;
  public String browser;

  public Application(String browser) {
    this.browser = browser;
  }

  public void login(String username, String password) {
      driver.get("http://localhost/litecart/public_html/admin");
      driver.findElement(By.name("username")).sendKeys(username);
      driver.findElement(By.name("password")).sendKeys(password);
      driver.findElement(By.name("login")).click();
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
