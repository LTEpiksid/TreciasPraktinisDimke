package lt.viko.eif.s.dieliautas.Race;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Cucumber spring konfigūracija.
 */
@CucumberContextConfiguration
@SpringBootTest(classes = RaceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {
}
