package page.LoginOrangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //Defnimos el logger específico para esta página
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[name='username']")
    private WebElement inputUsername;
    @FindBy(css = "input[name='password']")
    private WebElement inputPassword;
    @FindBy(css = "button[type='Submit']")
    private WebElement buttonSubmit;
    @FindBy(css = "img[src*='orangehrm-logo.png']")
    private WebElement imgOrange;
    @FindBy(css = "div[role='alert'].oxd-alert--error")
    private WebElement alertMessage;


    public void navegarALogin(){
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }


    public void ingresoCredencialesCorrectos(String username, String password){
        logger.info("Iniciando el proceso de ingreso de credenciales...");
        try {
            wait.until(ExpectedConditions.visibilityOf(inputUsername));
            logger.info("Escribiendo nombre de usuario " + username);
            inputUsername.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            inputUsername.sendKeys(Keys.chord(Keys.DELETE));
            inputUsername.sendKeys(username);
            logger.info("Escribiendo contraseña de usuario " + password);
            inputPassword.clear();
            inputPassword.sendKeys(password);
            wait.until(ExpectedConditions.elementToBeClickable(buttonSubmit));
            logger.info("Haciendo clic en el botón de ingresar");
            buttonSubmit.click();
        } catch (Exception e) {
            logger.error("Error durante el ingreso de credenciales "+ e.getMessage());
            throw e; // Lánzalo para que Cucumber marque el test como fallido
        }
    }

    public void ingresoCredencialesIncorrectas(String user, String pass){
        try {
            wait.until(ExpectedConditions.visibilityOf(inputUsername));
            logger.info("Escribiendo nombre de usuario " + user);
            inputUsername.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            inputUsername.sendKeys(Keys.chord(Keys.DELETE));
            inputUsername.sendKeys(user);
            logger.info("Escribiendo contraseña de usuario " + pass);
            inputPassword.clear();
            inputPassword.sendKeys(pass);
            wait.until(ExpectedConditions.elementToBeClickable(buttonSubmit));
            logger.info("Haciendo clic en el botón de ingresar");
            buttonSubmit.click();
        } catch (Exception e) {
            logger.error("Error durante el ingreso de credenciales "+ e.getMessage());
            throw e; // Lánzalo para que Cucumber marque el test como fallido
        }
    }

    public void ingresoExitoso(){
        logger.info("Validando si el ingreso fue exitoso buscando el logo de OrangeHRM.");
        try {
            boolean isPresent = wait.until(ExpectedConditions.visibilityOf(imgOrange)).isDisplayed();
            if(isPresent) {
                logger.info("Validación exitosa: El logo es visible.");
            }
        } catch (Exception e) {
            logger.error("Validación fallida: El logo no apareció tras el login.");
            throw e;
        }
    }

    public void ingresoNoExitoso(){
        logger.info("Validando que el ingreso no fue Exitoso.");
        try {
            boolean isPresent = wait.until(ExpectedConditions.visibilityOf(alertMessage)).isDisplayed();
            if(isPresent) {
                logger.info("El login con credenciales inválidas fue exitoso.");
            }
        } catch (Exception e) {
            logger.error("Validación fallida: El alerta de credenciales incorrectas no apareció.");
            throw e;
        }
    }

}
