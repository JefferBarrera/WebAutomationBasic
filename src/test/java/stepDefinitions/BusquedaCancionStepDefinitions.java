package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.thucydides.core.util.EnvironmentVariables;
import questions.VideoPlayed;
import tasks.OpenUrlTask;
import tasks.SearchVideoTask;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;


public class BusquedaCancionStepDefinitions {

    EnvironmentVariables environmentVariables;

    @Given("que se ingresa en la pagina principal de youtube")
    public void queSeIngresaEnLaPaginaPrincipalDeYoutube() {
        OnStage.theActorInTheSpotlight().wasAbleTo(
                OpenUrlTask.then(environmentVariables.getProperty("pages.youtube"))
        );
    }

    @When("se realiza la busqueda de la cancion {string}")
    public void seRealizaLaBusquedaDeLaCancion(String song) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SearchVideoTask.then(song)
        );
    }

    @Then("validamos que la cancion seleccionada es la correcta")
    public void validamosQueLaCancionSeleccionadaEsLaCorrecta() {
        String expected = OnStage.theActorInTheSpotlight().recall("titlePlayed");
        OnStage.theActorInTheSpotlight().should(
                seeThat("Validando video reproducido",
                        new VideoPlayed(),
                        equalTo(expected)
                )
        );
    }
}
