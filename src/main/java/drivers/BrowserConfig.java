package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BrowserConfig {

    public static WebDriver getDriver() {
//        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-infobars");
        options.addArguments("--remote-allow-origins=*"); // Selenium 4.5+ and Chrome 111.xxxx

//        return WebDriverManager.chromedriver().capabilities(options).create();
        return new ChromeDriver(options);
    }

}
