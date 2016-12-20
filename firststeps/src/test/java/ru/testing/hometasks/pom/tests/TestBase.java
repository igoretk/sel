package ru.testing.hometasks.pom.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.testing.hometasks.pom.app.ApplicationManager;

/**
 * Created by bebeka on 20.11.2016.
 */

public class TestBase {

  protected final ApplicationManager app;

  public TestBase(String browser) {
    app = new ApplicationManager(browser);
  }

  @BeforeClass
  public void setUp() {
    app.init();

  }

  @AfterClass
  public void tearDown() {
    app.stop();

  }

  public ApplicationManager getApp() {
    return app;
  }
}
