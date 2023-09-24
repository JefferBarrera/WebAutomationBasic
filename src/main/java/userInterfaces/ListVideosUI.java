package userInterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class ListVideosUI extends PageObject {
    public static final Target LBL_VIDEO_SELECTED = Target.the("Label del primer resultado").locatedBy("(//div[@id='contents']/ytd-video-renderer/div[@id='dismissible'])[1]//a[@id='video-title']//yt-formatted-string");
}
