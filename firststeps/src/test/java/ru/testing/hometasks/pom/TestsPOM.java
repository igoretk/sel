package ru.testing.hometasks.pom;

import org.testng.annotations.Test;

public class TestsPOM extends TestBase {

  public TestsPOM(String browser) {
    super(browser);
  }

  @Test
  public void testTask13CartAdd() throws InterruptedException {

    addToCart();
    openCart();
    removeFromCart();

  }

}
