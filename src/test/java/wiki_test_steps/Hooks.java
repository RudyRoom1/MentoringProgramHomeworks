package wiki_test_steps;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Case;
import com.codepine.api.testrail.model.CaseField;
import com.codepine.api.testrail.model.Project;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import guru_code.testrail.APIClient;
import guru_code.testrail.APIException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import webdriver.WebDrivers;

public class Hooks {

  private static WebDriver driver;

  public static WebDriver getDriver() {
    return driver;
  }

  @SneakyThrows
  @Before(order = 1)
  public void before() {
    driver = WebDrivers.createWebDriver();
  }

  @After(order = 1)
  public void tearDown() {
    WebDrivers.exitDriver();
  }

  public static TestRail testRail;
  public static Project myProject;
  public static int MY_PROJECT_ID = 1;
  public static Case testCase;
  public static List<CaseField> caseFields;
  public static List<ResultField> resultFields;
  public static List<Case> testCasesInSystem;
  public static boolean hasTestRailRunCreationAllowed;
  private final static String endPoint = "https://testprojectformentor.testrail.io/";
  private final static String username = "rudy.crypt@gmail.com";
  private final static String password = "TestRepo-rp5";
//  public static Configuration configuration = ConfigurationService;

  static {
    hasTestRailRunCreationAllowed = true;
    if (hasTestRailRunCreationAllowed) {
      testRail = TestRail.builder(endPoint, username, password).build();
      myProject = testRail.projects().get(MY_PROJECT_ID).execute();
      resultFields = testRail.resultFields().list().execute();
      caseFields = testRail.caseFields().list().execute();
      testCasesInSystem = testRail.cases().list(myProject.getId(), caseFields).execute();

    }
  }


  @After(order = 10)
  public void initCaseFieldByCucumberScenarioTitle(Scenario scenario) {
    if (hasTestRailRunCreationAllowed) {
      testCase = getTestRailCaseByCucumberScenarioTitle(scenario);
      resultFields = testRail.resultFields().list().execute();
    }
//    Run run = addNewRun("Daily Run");
//    closeTestRun(run);
  }

  @After(order = 9)
  public void setExecutionResult(Scenario scenario) {
    setTestCasesExecutionResult(testCase, scenario);
  }

//  @After
//  public void addTestRailCaseScenarioToCucumberReport(Scenario scenario){
//    scenario.write(testCase.getCustomField("steps"));
//  }


  private Case getTestRailCaseByCucumberScenarioTitle(Scenario scenario) {
    return testCasesInSystem
        .stream()
        .filter(currentTestCase -> currentTestCase.getTitle().equalsIgnoreCase(scenario.getName()))
        .findFirst()
        .orElseThrow(() -> new IllegalStateException(scenario.getName() + "is not registered"));
  }

  private Case addTestCases(Run run, String title) {
    return testRail.cases().add(run.getId(), new Case().setTitle(title), caseFields).execute();
  }

  private static Run addNewRun(String runName) {
    return testRail.runs()
        .add(myProject.getId(), new Run().setName(runName + LocalDateTime.now())).execute();
  }

  private void addResult(Run run, String title, Case testCase, List<ResultField> customResult, Scenario scenario) {
    testRail.results()
        .addForCase(run.getId(), testCase.getId(), new Result().setStatusId(getStatusMap().get(String.valueOf(scenario.getStatus()))), customResult)
        .execute();
  }

  private void closeTestRun(Run run) {
    testRail.runs().close(run.getId()).execute();
  }

  private void setTestCasesExecutionResult(Case testCase, Scenario scenario) {
    int testRailRunId = testRail.runs().update(addNewRun("Run")).execute().getId();
    testRail.tests()
        .list(testRailRunId)
        .execute()
        .stream()
        .filter(test -> test.getCaseId() == testCase.getId())
        .findFirst()
        .ifPresent(test -> testRail.results().addForCase(testRailRunId, testCase.getId(),
            new Result().setStatusId(getStatusMap().get(String.valueOf(scenario.getStatus()))), resultFields)
            .execute());
  }

//  private void serErrorMessageAsComment(Result result, Case testRailCase) {
//    int testRailRunId = 1;
//    testRail
//        .tests()
//        .list(testRailRunId)
//        .execute()
//        .stream()
//        .filter(test -> test.getCaseId() == testCase.getId())
//        .findFirst()
//        .ifPresent(test -> injectionFailReason(test, testRailRunId, result, testRailCase));
//  }

  private Map<String, Integer> getStatusMap() {
    Map<String, Integer> mapCucumberScenarioRusultsToTestRailCases = new HashMap<>();
    int statusPassed = 1;
    int statusBlocked = 2;
    int statusUntested = 3;
    int statusRetest = 4;
    int statusFailed = 5;

    mapCucumberScenarioRusultsToTestRailCases.put("PASSED", statusPassed);
    mapCucumberScenarioRusultsToTestRailCases.put("BLOCKED", statusBlocked);
    mapCucumberScenarioRusultsToTestRailCases.put("UNTESTED", statusUntested);
    mapCucumberScenarioRusultsToTestRailCases.put("RETEST", statusRetest);
    mapCucumberScenarioRusultsToTestRailCases.put("FAIL", statusFailed);
    return mapCucumberScenarioRusultsToTestRailCases;
  }
}


