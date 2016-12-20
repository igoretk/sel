package ru.testing.hometasks.pom.tests;

import org.testng.annotations.Test;

public class TestsPOM extends TestBase {


  @Test
  public void testTask13CartAdd() throws InterruptedException {

    app.getAddHelper().addToCart();
    app.getOpenHelper().openCart();
    app.getRemoveHelper().removeFromCart();

  }

}
