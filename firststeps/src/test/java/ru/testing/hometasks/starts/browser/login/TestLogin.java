package ru.testing.hometasks.starts.browser.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TestLogin extends TestBase {

    public TestLogin(String browser) {
        super(browser);
    }

    @BeforeMethod
    public void testLogin() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void testClick() throws InterruptedException {
        List<WebElement> menuItem = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));
        System.out.println(menuItem.size());
        for (int i = 1; i < menuItem.size(); i++) {

            WebElement item = driver.findElement(By.xpath(String.format("//ul[@id='box-apps-menu']/li[%s]", i)));
            item.click();

            List<WebElement> subMenuItem = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li/ul/li"));
            System.out.println(subMenuItem.size());
            if (subMenuItem.size() > 0) {
                for (int j = 1; j < subMenuItem.size() + 1; j++) {

                    WebElement subItem = driver.findElement(By.xpath(String.format("//ul[@id='box-apps-menu']/li/ul/li[%s]", j)));
                    subItem.click();
                }
            }
        }



    }

   /* @Test
    public void testClick2() throws InterruptedException {

        List<WebElement> menuItem = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));
        System.out.println("menuItem -> " + menuItem.size());

        for (int i = 1; i < menuItem.size(); i++) {
            List<WebElement> subMenuItem = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li/ul/li"));

            WebElement item = driver.findElement(By.xpath(String.format("//ul[@id='box-apps-menu']/li[%s]", i)));
            item.click();

            if (subMenuItem.size() > 0) {
                for (int j = 1; j < subMenuItem.size(); j++) {

                    WebElement subItem = driver.findElement(By.xpath(String.format("//ul[@id='box-apps-menu']/li/ul/li[%s]", j)));
                    subItem.click();
                }

            }
        }
    }*/
}










