package userInterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class ListVideosUI extends PageObject {
    public static final Target LBL_VIDEO_SELECTED = Target.the("Label del primer resultado").locatedBy("(//div[@id='contents' and @class='style-scope ytd-item-section-renderer'])[1]/ytd-video-renderer[1]//a[@id='video-title']/yt-formatted-string");
}
