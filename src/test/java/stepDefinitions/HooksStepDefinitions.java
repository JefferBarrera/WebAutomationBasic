package stepDefinitions;

import drivers.BrowserConfig;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.WebDriver;


public class HooksStepDefinitions {

    @Before
    public void actorSetup() {
        WebDriver driver = BrowserConfig.getDriver();
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Sam").can(BrowseTheWeb.with(driver));
    }

}
