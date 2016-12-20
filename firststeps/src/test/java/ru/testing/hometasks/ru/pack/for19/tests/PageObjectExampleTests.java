package ru.testing.hometasks.ru.pack.for19.tests;

import org.testng.annotations.Test;

public class PageObjectExampleTests extends TestBase {

  public PageObjectExampleTests(String browser) {
    super(browser);
  }

  @Test
  public void testTask13CartAdd() throws InterruptedException {

    app.addToCart();
    app.openCart();
    app.deleteItems();

  }

}
