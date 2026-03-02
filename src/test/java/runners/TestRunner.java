package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/feature/AMAZON",
        glue = {"stepDefinitions", "hooks"},
        tags = "@FlujoE2E",
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}, //Plugin de Allure
        monochrome = true
)
public class TestRunner {
}