package ru.testing.hometasks.tests.litecart.app;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestsLitecartApp extends TestBase {

  public TestsLitecartApp(String browser) {
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

  @Test
  public void testTask9Part1Alphabetical() {
    driver.navigate().to("http://localhost/litecart/public_html/admin/?app=countries&doc=countries");
    List<WebElement> countryTable = driver.findElements(By.cssSelector(".dataTable tr.row td a:not([title=Edit])"));
    List<String> CountryList = new ArrayList<>();
    for (int i = 1; i < countryTable.size(); i++) {
      CountryList.add(countryTable.get(i).getAttribute("innerText"));
    }
    List<String> copyOfCountryList = new ArrayList<>(CountryList);
    Collections.sort(CountryList, String.CASE_INSENSITIVE_ORDER);
    assertEquals(CountryList, copyOfCountryList);
  }


  @Test
  public void testTask9Part1ZoneNumbers() {
    driver.navigate().to("http://localhost/litecart/public_html/admin/?app=countries&doc=countries");
    List<WebElement> countryZones = driver.findElements(By.xpath("//*[@id='content']//tr/td[6]"));
    for (int i = 2; i < countryZones.size(); i++) {
      String str = driver.findElement(By.xpath(String.format("//*[@id='content']//tr[%s]/td[6]", i))).getAttribute("innerText");
      System.out.println(i + " значение атр: " + str);
      if (!str.equals("0")) {
        System.out.println("счетчик " + i);
        WebElement e = driver.findElement(By.xpath(String.format("//tr[%s]//td[7]/a", i)));
        System.out.println("вебэлемент ->" + e + " вебэлемент атр: " + e.getAttribute("href"));
        e.click();
        List<WebElement> openedCountryZones = driver.findElements(By.cssSelector("#table-zones tr td:nth-child(3)"));
        List<String> zoneList = new ArrayList<>();
        for (int j = 1; j < openedCountryZones.size() - 1; j++) {
          zoneList.add(openedCountryZones.get(j).getAttribute("innerText"));
        }
        List<String> copyOfZoneList = new ArrayList<>(zoneList);
         Collections.sort(zoneList, String.CASE_INSENSITIVE_ORDER);
        assertEquals(zoneList, copyOfZoneList);
        driver.navigate().back();

      }

    }

  }

  @Test
  public void testListsDraft() {
    List<String> list1 = new ArrayList<>();
    list1.add("Mama");
    list1.add("papa");
    list1.add("abrvalg");
    list1.add("Zzzz");

    System.out.println("до сорт " + list1);

    Collections.sort(list1, String.CASE_INSENSITIVE_ORDER);
    List<String> list2CopyOfList1 = new ArrayList<>(list1);
    // assertEquals(list1, list2CopyOfList1);

    System.out.println("после сорт " + list1);

  }

}







