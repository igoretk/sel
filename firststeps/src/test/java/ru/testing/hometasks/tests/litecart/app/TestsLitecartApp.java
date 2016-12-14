package ru.testing.hometasks.tests.litecart.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.*;

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
  public void testTask9Part2() {
    driver.navigate().to("http://localhost/litecart/public_html/admin/?app=geo_zones&doc=geo_zones");
    List<WebElement> geoZones = driver.findElements(By.xpath(".//*[@id='content']//tr//td[3]"));
    for (int i = 2; i < geoZones.size(); i++) {

      WebElement countryLinkToClick = driver.findElement(By.xpath(String.format("//tr[%s]/td[3]/a", i)));
      countryLinkToClick.click();

      List<WebElement> openedZones = driver.findElements(By.xpath(".//*[@id='table-zones']//tr//td[3]"));
      for (int j = 1; j < openedZones.size() - 1; j++) {

        WebElement zoneToSelect = driver.findElement(By.xpath(String.format("//tr[%s]/td[3]/select", j)));
        Select selectZones = new Select(zoneToSelect);
        List options = selectZones.getOptions();
        List copyOfOptions = new ArrayList(options);
        Collections.sort(options, String.CASE_INSENSITIVE_ORDER);
        assertEquals(options, copyOfOptions);
        driver.navigate().back();

      }
    }
  }

  @Test
  public void testTask11NewCustomerReg() throws InterruptedException {

    String emailData = createAcc();
    userLogout();
    newUserLogin(emailData);
    userLogout();
  }

  @Test
  public void testTask12NewProductAdd() throws InterruptedException {

    catalogMenuOpen();
    clickToAddNewProduct();
    fillGeneralTab();
    fillInformationTab();
    fillPriceTab();
    saveButton();
    navigateToCatalogMenu();

    List<WebElement> elements = driver.findElements(By.xpath(".//tr[@class='row']//td[3]/a"));
    for (int i = 3; i < elements.size(); i++) {
      assertTrue(isElementPresent(driver, By.xpath(String.format(".//*[@id='content']/form/table/tbody/tr[%s]/td[3]/a", i))));

    }

  }

  @Test
  public void testTask13CartAdd() throws InterruptedException {

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

    driver.navigate().to("http://localhost/litecart/public_html/en/checkout");

    List<WebElement> table = driver.findElements(By.xpath("//*[@id='order_confirmation-wrapper']//td[@class='item']"));
    for (int j = 0; j < table.size(); j++) {
      List<WebElement> tableRow = driver.findElements(By.xpath("//*[@id='order_confirmation-wrapper']//td[@class='item']"));
      WebElement removeButton = driver.findElement(By.xpath("//button[@name='remove_cart_item']"));
      removeButton.click();
      wait.until(ExpectedConditions.stalenessOf(tableRow.get(0)));
    }

  }
  @Test
  public void testTask14Windows() {

    String pageForStartTest = "http://localhost/litecart/public_html/admin/?app=countries&doc=countries";
    Random rand = new Random();

    driver.navigate().to(pageForStartTest);

    List<WebElement> allCountries = driver.findElements(By.xpath(".//table//tr//td[5]/a"));
    int n = rand.nextInt(allCountries.size());
    allCountries.get(n).click();

    List<WebElement> allLinksForWinOpen = driver.findElements(By.xpath(".//*[@id='content']//a[@target='_blank']"));

    int m = rand.nextInt(allLinksForWinOpen.size());
    if (isElementPresent(driver, By.xpath(".//*[@id='content']//a[@target='_blank']"))) {
      String originalWin = driver.getWindowHandle();
      Set<String> oldWins = driver.getWindowHandles();
      allLinksForWinOpen.get(m).click();
      for (String winHandle : driver.getWindowHandles()) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(oldWins.size() + 1));
        driver.switchTo().window(winHandle);
      }
      driver.close();
      driver.switchTo().window(originalWin);
    }

  }


  @Test
  public void testTask17LogsError() {

    String pageForStartTest = "http://localhost/litecart/public_html/admin/?app=catalog&doc=catalog&category_id=1";
    driver.navigate().to(pageForStartTest);

    List<WebElement> linksToEdit = driver.findElements(By.xpath(".//*[@class='dataTable']//tr//td[3]//a"));
    for (int i = 5; i <= linksToEdit.size() + 1; i++) {
      WebElement items = driver.findElement(By.xpath(String.format(".//*[@class='dataTable']//tr[%s]//td[3]//a", i)));
      items.click();
      analyzeLog();
      driver.navigate().back();
    }

  }

  public void analyzeLog() {
    LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
    for (LogEntry entry : logEntries) {
      System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
    }
  }
}









