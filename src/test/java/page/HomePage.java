package page;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {


    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "nav-logo-sprites")
    private WebElement logoAmazon;
    @FindBy(css = "input#twotabsearchtextbox")
    private WebElement inputBusqueda;
    @FindBy(css = "input#nav-search-submit-button")
    private WebElement searchButton;


    public void paginaInicio(){
        driver.get("https://www.amazon.com/");
        wait.until(ExpectedConditions.visibilityOf(logoAmazon));
        Assert.assertTrue("Logo no visible", logoAmazon.isDisplayed());
    }

    //Esta es la acción principal de la Home
    public SearchResultsPage buscarProducto(String producto){
        wait.until(ExpectedConditions.elementToBeClickable(inputBusqueda));
        inputBusqueda.clear();
        inputBusqueda.sendKeys(producto);
        searchButton.click();

        // Retonarmos la nueva página para encadenar acciones
        return new SearchResultsPage(driver);
    }


}