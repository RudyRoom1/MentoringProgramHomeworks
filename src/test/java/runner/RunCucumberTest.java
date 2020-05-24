package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    glue = {"wiki_test_steps","testRail"},
    features = "src/test/resources/features",
    plugin = {
        "TestStepWatch"
    })
public class RunCucumberTest {

}
