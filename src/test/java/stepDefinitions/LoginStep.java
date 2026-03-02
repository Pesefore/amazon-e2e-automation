package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.LoginOrangeHRM.LoginPage;

public class LoginStep {

    private static final Logger log = LoggerFactory.getLogger(LoginStep.class);
    private WebDriver driver;
    private LoginPage loginPage;

    @Given("el usuario se encuentra en la pagina de inicio de sesion")
    public void inicioSesion(){
        this.driver = Hooks.driver;
        this.loginPage = new LoginPage(driver);
        loginPage.navegarALogin();
    }

    @Given("el usuario inicia sesion con su username {string} y password {string}")
    public void backgroundLogin(String usuario, String contraseña){
        this.driver = Hooks.driver;
        this.loginPage = new LoginPage(driver);
        loginPage.navegarALogin();
        loginPage.ingresoCredencialesCorrectos(usuario, contraseña);
        loginPage.ingresoExitoso();
    }

    @When("ingresa sesion con {string} y {string} validos")
    public void ingresoCredencialesCorrectos(String username, String password){
    loginPage.ingresoCredencialesCorrectos(username, password);
    }
    @Then("se redirecciona su perfil de usuario")
    public void ingresoExitoso(){
    loginPage.ingresoExitoso();
    }
    @When("ingresa sesion {string} y {string} invalidos")
    public void ingresoCredencialesIncorrectas(String user, String pass){
        loginPage.ingresoCredencialesIncorrectas(user, pass);
    }
    @Then("vuelve a ingresar nuevamente sus credenciales")
    public void ingresoNoExitoso(){
        loginPage.ingresoNoExitoso();
    }


}
