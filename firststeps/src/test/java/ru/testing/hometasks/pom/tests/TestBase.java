package ru.testing.hometasks.pom.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.testing.hometasks.pom.app.ApplicationManager;

public class TestBase {

  public  ApplicationManager app = new ApplicationManager();



  @BeforeClass
  public void setUp(){
    app.init();

  }

  @AfterClass
  public void tearDown() {
    app.stop();

  }

}
