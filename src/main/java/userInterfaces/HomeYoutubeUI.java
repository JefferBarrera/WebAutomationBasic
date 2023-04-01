package userInterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;


public class HomeYoutubeUI extends PageObject {
    public static final Target TXT_SEARCH = Target.the("barra de busqueda").locatedBy("//input[@id='search']");

}
