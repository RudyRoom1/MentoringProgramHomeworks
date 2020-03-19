package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDrivers {

  private static WebDriver driver;

  public static WebDriver createWebDriver() {
    String webdriver = System.getProperty("browser", "firefox");
    switch (webdriver) {
      case "firefox":
        return getFireFoxDriver();
      case "chrome":
        return getChromeDriver();
      case "edge":
        return getEdgeDriver();
      default:
        throw new RuntimeException("Unsupported webdriver: " + webdriver);
    }
  }

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
      driver = new FirefoxDriver();
    }
    return driver;
  }

  public static WebDriver getEdgeDriver() {
    if (driver == null) {
      WebDriverManager.edgedriver().setup();
      driver = new EdgeDriver();
    }
    return driver;
  }

  public static void exitDriver() {
    driver.quit();
    driver = null;
  }
}
