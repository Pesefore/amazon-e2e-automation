package page.RetoAmazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResultsPage {


    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "a[aria-label*='Skechers']")
    private WebElement marcaZapato;  // Marca Skechers

    @FindBy(id = "s-result-sort-select")
    private WebElement dropdownAlternativas;  // Dropdown de Ordenamiento

    /* @FindBy(css = "[data-component-type='s-search-result']")
    private List<WebElement> listaProductos; */
    // Contenedor de productos
    // Este selector CSS busca todos los divs que son resultados de búsqueda reales,
    // ignorando la publicidad que Amazon pone entre medias.
    //        *** Evitar usar el FindBy cuando el DOM se vuelva a renderizar frecuentemente ***

    @FindBy(css = "h2.a-size-base.a-spacing-small.a-spacing-top-small")
    private WebElement totalResultadosText;
    // basándose en la clase 'a-size-base' dentro del h2 específico.

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // IMPORTANTE: Inicializar los elementos aquí
        PageFactory.initElements(driver, this);
    }

    public void filtrarProducto() {
        String urlAntes = driver.getCurrentUrl();
        wait.until(ExpectedConditions.elementToBeClickable(marcaZapato)).click();

        wait.until(driver ->
                !driver.getCurrentUrl().equals(urlAntes)
        );

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("[data-component-type='s-search-result']")
        ));

    }

    public void aplicarRangoPrecio(String min, String max) {
        String urlActual = driver.getCurrentUrl();
        String nuevaUrl = urlActual + "&low-price=" + min + "&high-price=" + max;
        driver.get(nuevaUrl);
        System.out.println("La URL que estoy buscando es la siguiente: "+nuevaUrl);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("[data-component-type='s-search-result']")
        ));

    }

    public void ordenarPor(String criterio) {
        wait.until(ExpectedConditions.visibilityOf(dropdownAlternativas));
        String urlAntes = driver.getCurrentUrl();
        Select select = new Select(dropdownAlternativas);
        select.selectByVisibleText(criterio);

        // Esperamos hasta que al menos UN ELEMENTO que coincida con el selector esté presente en el DOM
        // presenceOfElementLocated (Existencia en el DOM, aunque esté oculto)
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.s-result-item[data-component-type='s-search-result']")));

        // Esperar hasta que la URL cambie (esto garantiza que Amazon recargó resultados)
        wait.until(driver ->
                !driver.getCurrentUrl().equals(urlAntes)
        );

        // Espera hasta que TODOS los elementos actualmente encontrados en está lista estés visibles
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("[data-component-type='s-search-result']")
        ));
    }

    public void imprimirResultadosEncontrados() {
        wait.until(ExpectedConditions.visibilityOf(totalResultadosText));
        System.out.println("Resultados totales: " + totalResultadosText.getText());
    }

    public void imprimirPrimerosProductos(int cantidad) {
        List<WebElement> listaProductos = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("[data-component-type='s-search-result']")
                )
        );
        System.out.println("--- Primeros " + cantidad + " productos ---");

        int contador = 0;

        for (WebElement producto : listaProductos) {
            if (contador >= cantidad) break;
            List<WebElement> titulo = producto.findElements(By.cssSelector("h2 span"));
            List<WebElement> precioWhole = producto.findElements(By.cssSelector(".a-price-whole"));
            if (!titulo.isEmpty() && !precioWhole.isEmpty()) {
                String nombre = titulo.get(0).getText();
                String precio = precioWhole.get(0).getText();
                System.out.println("Producto " + (contador + 1) + ": "
                        + nombre + " - Precio: $" + precio);
                contador++;
            }
        }
    }

    public void verificarOrdenDescendentePrecio(int cantidad) {
        List<WebElement> listaProductos = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("[data-component-type='s-search-result']")
                )
        );

        List<Double> precios = new ArrayList<>();
        int contador = 0;
        for (WebElement producto : listaProductos) {
            if (contador == cantidad) break;
            List<WebElement> priceList = producto.findElements(By.cssSelector(".a-price .a-offscreen"));

            if (!priceList.isEmpty()) {
                String precioTexto = priceList.get(0).getText()
                        .replace("PEN", "")
                        .replace("S/", "")
                        .replace(",", "")
                        .trim();
                if (!precioTexto.isEmpty()) {
                    precios.add(Double.parseDouble(precioTexto));
                    contador++;
                }
            }
        }

        // VALIDACIÓN DEL FOR
        List<Double> preciosOrdenados = new ArrayList<>(precios);
        Collections.sort(preciosOrdenados, Collections.reverseOrder());
        if (precios.equals(preciosOrdenados)) {
            System.out.println("Los productos están ordenados descendientemente por precio.");
        } else {
            System.out.println("Los productos NO están ordenados descendientemente por precio.");
        }
    }
}

