package tasks;


import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Browser;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;
import userInterfaces.HomeYoutubeUI;
import userInterfaces.ListVideosUI;
import userInterfaces.VideoPlayedUI;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SearchVideoTask implements Task {
    private final String song;

    public SearchVideoTask(String song) {
        this.song = song;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(song).into(HomeYoutubeUI.TXT_SEARCH),
                Enter.keyValues(Keys.ENTER).into(HomeYoutubeUI.TXT_SEARCH),
                WaitUntil.the(ListVideosUI.LBL_VIDEO_SELECTED, isVisible()).forNoMoreThan(Duration.ofSeconds(5))
        );
        actor.remember("titleResult", BrowseTheWeb.as(actor).findBy(ListVideosUI.LBL_VIDEO_SELECTED.getCssOrXPathSelector()).getText());
        actor.attemptsTo(
                Click.on(ListVideosUI.LBL_VIDEO_SELECTED),
                WaitUntil.the(VideoPlayedUI.LBL_TITLE_VIDEO, isVisible()).forNoMoreThan(Duration.ofSeconds(10))
        );
        actor.remember("titlePlayed", BrowseTheWeb.as(actor).findBy(VideoPlayedUI.LBL_TITLE_VIDEO.getCssOrXPathSelector()).getText());
    }

    public static Performable then(String song) {
        return Instrumented.instanceOf(SearchVideoTask.class).withProperties(song);
    }
}
