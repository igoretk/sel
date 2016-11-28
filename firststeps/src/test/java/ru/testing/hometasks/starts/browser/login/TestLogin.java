package ru.testing.hometasks.starts.browser.login;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
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
    List<WebElement> product = driver.findElements(By.xpath(".//*[@class='content']"));

    System.out.println(product.size());
    int counts = 0;

    for (int i = 1; i < product.size() + 1; i++) {

      assertTrue(isElementPresent(driver, By.cssSelector("[class~=sticker]")));
      counts = i;
    }
    assertEquals(counts, product.size());

  }

  @Test
  public void tempDraftForGettingAttributes() {
    driver.navigate().to("http://localhost/litecart/public_html/en/");
    //WebElement element = driver.findElement(By.cssSelector("[name = 'login']"));
    //System.out.println(element.getAttribute("outerHTML"));
    WebElement element1 = driver.findElement(By.cssSelector("[name = 'query']"));
    System.out.println(element1.getAttribute("value"));
    Point location = element1.getLocation();
    Rectangle rectangle = element1.getRect();
    System.out.println("location = " + location + " rectangle = " + rectangle);

  }

  @Test
  public void testIsClickedProductCorrect() {

    driver.navigate().to("http://localhost/litecart/public_html/en/");
    List<WebElement> productCampaigns = driver.findElements(By.xpath(".//*[@id='box-campaigns']//ul//li//a[@class='link']"));

    String linkBaseURIOpenedPage = null;
    String linkHrefMainPage = null;
    String textMainPage = null;
    String textOpenedPage = null;

    String priceMainPageRegular = null;
    String priceOpenedPageRegular = null;

    String priceMainPageCampaign = null;
    String priceOpenedPageCampaign = null;

    String colorPriceRegMainPage = null;
    String stylePriceRegMainPage = null;

    String colorPriceRegExpected = "rgba(119, 119, 119, 1)";
    String stylePriceRegExpected = "line-through";

    String colorPriceRegOpenedPage = null;
    String stylePriceRegOpenedPage = null;

    String colorPriceRegOpenedExpected = "rgba(102, 102, 102, 1)";
    String stylePriceRegOpenedPageExpected = "line-through";

    String colorPriceCampMainPage = null;
    String colorPriceCampMainPageExpected = "rgba(204, 0, 0, 1)";

    String stylePriceCampMainPage = null;
    String stylePriceCampMainPageExpected = "900";

    String colorPriceCampOpenedPage = null;
    String colorPriceCampMainOpenedExpected = "rgba(204, 0, 0, 1)";

    String stylePriceCampOpenedPage = null;
    String stylePriceCampMainOpenedExpected = "700";


    for (int i = 1; i < productCampaigns.size(); i++) {

      WebElement productMainPage = productCampaigns.get(0);
      linkHrefMainPage = productMainPage.getAttribute("href");
      textMainPage = productMainPage.findElement(By.cssSelector(".name")).getAttribute("innerText");
      priceMainPageRegular = productMainPage.findElement(By.cssSelector(".regular-price")).getAttribute("innerText");
      priceMainPageCampaign = productMainPage.findElement(By.cssSelector(".campaign-price")).getAttribute("innerText");
      colorPriceRegMainPage = productMainPage.findElement(By.cssSelector(".regular-price")).getCssValue("color");
      stylePriceRegMainPage = productMainPage.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration");
      colorPriceCampMainPage = productMainPage.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
      stylePriceCampMainPage = productMainPage.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");

      if (!productMainPage.isDisplayed()) {
        return;
      }

      productMainPage.click();
      WebElement productOpenedPage = driver.findElement(By.xpath(".//*[@class = 'images-wrapper']"));
      linkBaseURIOpenedPage = productOpenedPage.getAttribute("baseURI");
      textOpenedPage = driver.findElement(By.cssSelector(".title[itemprop='name']")).getAttribute("innerText");
      priceOpenedPageRegular = driver.findElement(By.cssSelector(".regular-price")).getAttribute("innerText");
      priceOpenedPageCampaign = driver.findElement(By.cssSelector(".campaign-price[itemprop=price]")).getAttribute("innerText");
      colorPriceRegOpenedPage = driver.findElement(By.cssSelector(".regular-price")).getCssValue("color");
      stylePriceRegOpenedPage = driver.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration");
      colorPriceCampOpenedPage = driver.findElement(By.cssSelector(".campaign-price[itemprop=price]")).getCssValue("color");
      stylePriceCampOpenedPage = driver.findElement(By.cssSelector(".campaign-price[itemprop=price]")).getCssValue("font-weight");

    }

    assertEquals(linkBaseURIOpenedPage, linkHrefMainPage);
    assertEquals(textOpenedPage, textMainPage);
    assertEquals(priceOpenedPageRegular, priceMainPageRegular);
    assertEquals(priceOpenedPageCampaign, priceMainPageCampaign);
    assertEquals(colorPriceRegMainPage, colorPriceRegExpected);
    assertEquals(stylePriceRegMainPage, stylePriceRegExpected);
    assertEquals(colorPriceRegOpenedPage, colorPriceRegOpenedExpected);
    assertEquals(stylePriceRegOpenedPage, stylePriceRegOpenedPageExpected);
    assertEquals(colorPriceCampMainPage, colorPriceCampMainPageExpected);
    assertEquals(stylePriceCampMainPage, stylePriceCampMainPageExpected);
    assertEquals(stylePriceCampOpenedPage, stylePriceCampMainOpenedExpected);
    assertEquals(colorPriceCampOpenedPage, colorPriceCampMainOpenedExpected);


  }

}







