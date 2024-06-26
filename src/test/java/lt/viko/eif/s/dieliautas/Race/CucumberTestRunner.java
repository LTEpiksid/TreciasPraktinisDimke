package lt.viko.eif.s.dieliautas.Race;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Cucumber testų paleidėjas.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "lt.viko.eif.s.dieliautas.Race.steps",
        plugin = {"pretty", "html:target/cucumber.html"},
        monochrome = true
)
public class CucumberTestRunner {
}
