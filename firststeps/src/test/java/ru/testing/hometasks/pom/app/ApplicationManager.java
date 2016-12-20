package ru.testing.hometasks.pom.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplicationManager {
  public WebDriver driver;
  public WebDriverWait wait;
  private RemoveHelper removeHelper;
  private AddHelper addHelper;
  private OpenHelper openHelper;


  public void init() {

    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 15);
    driver.get("http://localhost/litecart/public_html/admin");

    addHelper = new AddHelper(driver, wait);
    openHelper = new OpenHelper(driver);
    removeHelper = new RemoveHelper(driver, wait);

    login("admin", "admin");
  }

  public void login(String username, String password) {
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("login")).click();
  }

  public void stop() {
    driver.quit();
    driver = null;
  }

  public AddHelper getAddHelper() {
    return addHelper;
  }

  public OpenHelper getOpenHelper() {
    return openHelper;
  }

  public RemoveHelper getRemoveHelper() {
    return removeHelper;
  }
}
