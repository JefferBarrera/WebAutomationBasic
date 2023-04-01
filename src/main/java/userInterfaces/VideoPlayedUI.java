package userInterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class VideoPlayedUI extends PageObject {
    public static final Target LBL_TITLE_VIDEO = Target.the("label del titulo").locatedBy("//div[@id='title']/h1");
}
