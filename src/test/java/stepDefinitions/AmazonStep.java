package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.HomePage;
import page.SearchResultsPage;

public class AmazonStep {

    private WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage resultsPage;

    @Given("que el usuario navega a Amazon")
    public void navegaAmazon() {
        this.driver = Hooks.driver;
        this.homePage = new HomePage(driver);
        homePage.paginaInicio();
    }
    @When("busca el producto {string}")
    public void buscarProducto(String zapatos){
        resultsPage = homePage.buscarProducto(zapatos);
    }

    @And("filtra la búsqueda por la marca Skechers")
    public void filtrarProducto(){
        resultsPage.filtrarProducto();
    }

    @And("aplica un rango de precio de ${int} a ${int}")
    public void filtrarPrecio(int precioMinimo, int precioMaximo){
        //Se convertirá a String para la manipulación de URL
        resultsPage.aplicarRangoPrecio(String.valueOf(precioMinimo), String.valueOf(precioMaximo));
    }
    @Then("se imprime el número total de resultados encontrados")
    public void imprimirResultadosEncontrados(){
        resultsPage.imprimirResultadosEncontrados();
    }
    @And("se ordenan los productos del precio del más alto al más bajo")
    public void ordenarProductosPorPrecio(){
        resultsPage.ordenarPor("Precio: Del más alto al más bajo");
    }
    @And("se imprimen los primeros 5 productos con sus precios")
    public void imprimirCincoPrimerosProductos(){
        resultsPage.imprimirPrimerosProductos(5);
    }
    @And("se verifica que los primeros 5 productos estén ordenados descendientemente por precio")
    public void verificarProductosOrdenados(){
        resultsPage.verificarOrdenDescendentePrecio(5);
    }

    @And("se ordenan los productos por Llegadas más recientes")
    public void ordenarPorNuevosLanzamientos(){
        resultsPage.ordenarPor("Llegadas más recientes");
    }
    @And("se imprimen los primeros 5 productos ordenados por novedad")
    public void ordenarProductosPorNovedad(){
        resultsPage.imprimirPrimerosProductos(5);
    }
    @And("se ordenan los productos por Promedio Opinión del cliente")
    public void ordenarProductosPorPromedio(){
        resultsPage.ordenarPor("Promedio Opinión del cliente");
    }
    @And("se imprimen los primeros 5 productos ordenados por calificación")
    public void ordenarProductosPorCalificacion(){
        resultsPage.imprimirPrimerosProductos(5);
    }


}