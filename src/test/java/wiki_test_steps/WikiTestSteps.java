package wiki_test_steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.WikiEventPage;

public class WikiTestSteps {

  private Logger logger = LoggerFactory.getLogger(WikiTestSteps.class);
  WikiEventPage eventPage = new WikiEventPage();

  @Given("User opens Wikipedia page and search all today's")
  public void userOpensWikipediaPageAndSearchAllTodaySEvents() {

    String todayData = (
        Arrays.asList(
            LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
                .split(",")).get(0));

    logger.info("user is opening main page");
    Hooks.getDriver().get("https://en.wikipedia.org/");

    logger.info("user typing a search query");

    Hooks
        .getDriver()
        .findElement(By.id("searchInput"))
        .sendKeys(todayData);

    logger.info("user clicks search button");

    Hooks
        .getDriver()
        .findElement(By.id("searchButton"))
        .click();
  }

  @Given("User opens Wikipedia page and search all today's events")
  public void userOpensWikipediaPageAndSearchAllTodaySEventsa() {

    PageFactory.initElements(Hooks.getDriver(), eventPage);

    String todayData = (
        Arrays.asList(
            LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
                .split(",")).get(0));

    logger.info("user is opening main page");

    logger.info("user typing a search query");
    eventPage.getSearchInput().sendKeys(todayData);

    logger.info("user clicks search button");
    eventPage.getSearchButton().click();

  }

  //  private void takeScreenshot(String message) {
//    byte[] screenshot = ((TakesScreenshot) TestBase.getDriver()).getScreenshotAs(OutputType.BYTES);
//
//    logger.info("RP_MESSAGE#BASE64#{}#{}",
//        BaseEncoding.base64().encode(screenshot),
//        message);
//  }

  private void delay() {

    try {
      Thread.sleep(3_000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Then("Calculate the number of articles with Geo-points mentioning")
  public void calculateTheNumberOfArticlesWithGeoPointsMentioning() {
    List<String> geoPoints = Arrays.asList("China", "Canada", "New York", "Philippines");
    logger.info("get text from all tags");
    List<String> allGeoTags = eventPage.getAllGeoTags(eventPage.getListOfTags());
    List<String> matchedList = allGeoTags.stream().filter(geoPoints::contains)
        .collect(Collectors.toList());
  }

  @And("Forward next day on calender bar")
  public void forwardNextDayOnCalenderBar() {
    eventPage.getNextDay();
  }
}
