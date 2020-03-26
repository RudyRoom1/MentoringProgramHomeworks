import cucumber.api.PickleStepTestStep;
import cucumber.api.event.ConcurrentEventListener;
import cucumber.api.event.EventHandler;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestStepStarted;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestStepWatch implements ConcurrentEventListener {

  public static final String DEVIDER =
      "===================================================================================";
  private EventHandler<TestStepStarted> stepHandler =
      new EventHandler<TestStepStarted>() {
        @Override
        public void receive(TestStepStarted event) {
          handleTestStep(event);
        }

        private synchronized void handleTestStep(TestStepStarted event) {
          if (event.testStep instanceof PickleStepTestStep) {
            log.info(DEVIDER);
            log.info("Running STEP name: " + ((PickleStepTestStep) event.testStep).getStepText());
          }
        }
      };

  @Override
  public void setEventPublisher(EventPublisher eventPublisher) {
    eventPublisher.registerHandlerFor(TestStepStarted.class, this.stepHandler);
  }
}