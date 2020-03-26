package pages;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wiki_test_steps.Hooks;

@Data
public class WikiEventPage {

  final String NEXT_MONTH_XPATH_PATTERN = "//table[@class='toccolours floatright']//a[@title='%s']";
  final String NEXT_DAY_PATTERN = "//td/a[@title ='%s']";
  WebElement nextMonthButton;
  WebElement nextDayButton;

  @FindBy(xpath = "//span[@id='Events']/../following-sibling::ul[1]//a")
  //TODO ASK HOW TO CHANGE TO CREATE WITHOUT [1]
  private List<WebElement> listOfTags;

  @FindBy(id = "searchInput")
  private WebElement searchInput;

  @FindBy(id = "searchButton")
  private WebElement searchButton;

  public WikiEventPage() {
    Hooks.getDriver().get("https://en.wikipedia.org/");
    PageFactory.initElements(Hooks.getDriver(), this);
  }

  public List<String> getAllGeoTags(List<WebElement> webElement) {
    return webElement.stream().map(WebElement::getText)
        .collect(Collectors.toList());
  }

  public void getNextMonth(LocalDate date) {
    String nextMonth = date.plusMonths(1).getMonth().toString().toLowerCase();
    String nextMonthTitle = firstLetterToUpperCase(nextMonth);
    String nextMonthLocator = String
        .format(NEXT_MONTH_XPATH_PATTERN,
            nextMonthTitle);
    nextMonthButton = Hooks.getDriver().findElement(By.xpath(nextMonthLocator));
    nextMonthButton.click();
  }

  public void getNextDay(String day) {
    String nextDayLocator = String
        .format(NEXT_DAY_PATTERN,
            day);
    nextDayButton = Hooks.getDriver().findElement(By.xpath(nextDayLocator));
    nextDayButton.click();
  }

  public String firstLetterToUpperCase(String word) {
    if (word == null || word.isEmpty()) {
      return "";
    }
    return word.substring(0, 1).toUpperCase() + word.substring(1);
  }

}
