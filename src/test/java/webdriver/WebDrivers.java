package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDrivers {

  private static WebDriver driver;

  public static WebDriver getChromeDriver() {
    if (driver == null) {
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
    }
    return driver;
  }

  public static WebDriver getFireFoxDriver() {
    if (driver == null) {
      WebDriverManager.firefoxdriver().setup();
      driver = new ChromeDriver();
    }
    return driver;
  }

  public static WebDriver getEdgeDriver() {
    if (driver == null) {
      WebDriverManager.edgedriver().setup();
      driver = new ChromeDriver();
    }
    return driver;
  }

  public static void exitDriver() {
    driver.quit();
    driver = null;
  }
}
