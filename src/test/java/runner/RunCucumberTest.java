package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    //        plugin = {"pretty", "com.epam.reportportal.cucumber.ScenarioReporter"},
    glue = "wiki_test_steps",
    features = "src/test/resources/features")
public class RunCucumberTest {

}
