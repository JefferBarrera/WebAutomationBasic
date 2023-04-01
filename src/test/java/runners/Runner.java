package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        tags = "@SearchSong",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class Runner {
}
