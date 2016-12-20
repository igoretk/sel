package ru.testing.hometasks.pom.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RemoveHelper {


  private WebDriver driver;
  private WebDriverWait wait;

  public RemoveHelper(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;

  }

  public void removeFromCart() {
    List<WebElement> table = driver.findElements(By.xpath("//*[@id='order_confirmation-wrapper']//td[@class='item']"));
    for (int j = 0; j < table.size(); j++) {
      List<WebElement> tableRow = driver.findElements(By.xpath("//*[@id='order_confirmation-wrapper']//td[@class='item']"));
      WebElement removeButton = driver.findElement(By.xpath("//button[@name='remove_cart_item']"));
      removeButton.click();
      wait.until(ExpectedConditions.stalenessOf(tableRow.get(0)));
    }
  }
}
