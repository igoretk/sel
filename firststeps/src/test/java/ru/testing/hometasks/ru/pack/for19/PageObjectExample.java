package ru.testing.hometasks.ru.pack.for19;

import org.testng.annotations.Test;

public class PageObjectExample extends TestBase{
  public PageObjectExample(String browser) {
    super(browser);
  }

  @Test
  public void testTask13CartAdd() throws InterruptedException {

    addToCart();

    openCart();

    deleteItems();

  }

}
