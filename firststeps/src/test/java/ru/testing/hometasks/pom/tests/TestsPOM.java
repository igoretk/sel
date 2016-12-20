package ru.testing.hometasks.pom.tests;

import org.testng.annotations.Test;
import ru.testing.hometasks.pom.tests.TestBase;

public class TestsPOM extends TestBase {

  public TestsPOM(String browser) {
    super(browser);
  }

  @Test
  public void testTask13CartAdd() throws InterruptedException {

    app.addToCart();
    app.openCart();
    app.removeFromCart();

  }

}
