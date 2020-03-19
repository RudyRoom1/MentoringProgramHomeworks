package wiki_test_steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import webdriver.WebDrivers;

public class Hooks {

  private static WebDriver driver;

  public static WebDriver getDriver() {
    return driver;
  }

  @Before
  public void before() {
    driver = WebDrivers.getChromeDriver();
  }

  @After(order = 1)
  public void tearDown() {
    WebDrivers.exitDriver();
  }
}
