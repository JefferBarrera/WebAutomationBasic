package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class VideoPlayed implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return actor.recall("titleResult");
    }
}
