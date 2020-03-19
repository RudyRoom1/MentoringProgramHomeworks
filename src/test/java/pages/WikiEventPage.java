package pages;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wiki_test_steps.Hooks;

@Data
public class WikiEventPage {

  @FindBy(xpath = "//*[@id=\"mw-content-text\"]/div/ul[1]//a")
  private List<WebElement> listOfTags;

  @FindBy(id = "searchInput")
  private WebElement searchInput;

  @FindBy(id = "searchButton")
  private WebElement searchButton;

  @FindBy(xpath = "//*[@id=\"mw-content-text\"]//*[@class=\"mw-selflink selflink\"]/ancestor::td/following-sibling::td[position()='1']")
  private WebElement nextDayInCalendar;

  public WikiEventPage() {
    Hooks.getDriver().get("https://en.wikipedia.org/");
    PageFactory.initElements(Hooks.getDriver(), this);
  }

  public List<String> getAllGeoTags(List<WebElement> webElement) {
    return webElement.stream().map(WebElement::getText)
        .collect(Collectors.toList());
  }

  public void getNextDay() {
    nextDayInCalendar.click();
  }
}
