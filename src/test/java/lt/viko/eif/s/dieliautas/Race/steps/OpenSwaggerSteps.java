package lt.viko.eif.s.dieliautas.Race.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import lt.viko.eif.s.dieliautas.Race.RaceApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = RaceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OpenSwaggerSteps {

    private WebDriver driver;
    private final int port = 8080;
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;
    private int createdStatusId;

    @Given("the application is running")
    public void theApplicationIsRunning() {
        driver = new HtmlUnitDriver();
    }

    @When("I open the main page")
    public void iOpenTheMainPage() {
        driver.get("http://localhost:" + port);
    }

    @When("I click the Swagger button")
    public void iClickTheSwaggerButton() {
        driver.findElement(By.xpath("//button[contains(text(), 'Swagger')]")).click();
    }

    @Then("the Swagger UI should open")
    public void theSwaggerUIShouldOpen() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        Assert.assertTrue("Expected URL to contain '/swagger-ui/index.html' but was " + currentUrl, currentUrl.contains("/swagger-ui/index.html"));
        driver.quit();
    }

    @When("I get the racer with id {int}")
    public void iGetTheRacerWithId(int id) {
        String url = "http://localhost:" + port + "/api/racers/" + id;
        response = restTemplate.getForEntity(url, String.class);
    }

    @Then("the response should contain racer details")
    public void theResponseShouldContainRacerDetails() {
        Assert.assertNotNull("Response should not be null", response.getBody());
        Assert.assertTrue("Response should contain racer details", response.getBody().contains("id"));
        driver.quit();
    }

    @When("I get the race with id {int}")
    public void iGetTheRaceWithId(int id) {
        String url = "http://localhost:" + port + "/api/races/" + id;
        response = restTemplate.getForEntity(url, String.class);
    }

    @Then("the response should contain race details")
    public void theResponseShouldContainRaceDetails() {
        Assert.assertNotNull("Response should not be null", response.getBody());
        Assert.assertTrue("Response should contain race details", response.getBody().contains("id"));
        driver.quit();
    }

    @When("I create a new status with name {string}")
    public void iCreateANewStatusWithName(String name) {
        String url = "http://localhost:" + port + "/api/statuses";
        String requestBody = "{\"statusName\": \"" + name + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        response = restTemplate.postForEntity(url, entity, String.class);
        createdStatusId = extractIdFromResponse(response.getBody());
    }

    @Then("the status should be created successfully")
    public void theStatusShouldBeCreatedSuccessfully() {
        Assert.assertEquals("Response status should be 200 OK", HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @When("I delete the status with name {string}")
    public void iDeleteTheStatusWithName(String name) {
        String url = "http://localhost:" + port + "/api/statuses/" + createdStatusId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
    }

    @Then("the status should be deleted successfully")
    public void theStatusShouldBeDeletedSuccessfully() {
        Assert.assertEquals("Response status should be 204 NO CONTENT", HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
        driver.quit();
    }

    @When("I get the status with id {int}")
    public void iGetTheStatusWithId(int id) {
        String url = "http://localhost:" + port + "/api/statuses/" + id;
        response = restTemplate.getForEntity(url, String.class);
    }

    @Then("the response should contain status details")
    public void theResponseShouldContainStatusDetails() {
        Assert.assertNotNull("Response should not be null", response.getBody());
        Assert.assertTrue("Response should contain status details", response.getBody().contains("id"));
        driver.quit();
    }

    @When("I get all racers")
    public void iGetAllRacers() {
        String url = "http://localhost:" + port + "/api/racers";
        response = restTemplate.getForEntity(url, String.class);
    }

    @Then("the response should contain a list of racers")
    public void theResponseShouldContainAListOfRacers() {
        Assert.assertNotNull("Response should not be null", response.getBody());
        Assert.assertTrue("Response should contain a list of racers", response.getBody().contains("["));
        driver.quit();
    }

    @When("I get all races")
    public void iGetAllRaces() {
        String url = "http://localhost:" + port + "/api/races";
        response = restTemplate.getForEntity(url, String.class);
    }

    @Then("the response should contain a list of races")
    public void theResponseShouldContainAListOfRaces() {
        Assert.assertNotNull("Response should not be null", response.getBody());
        Assert.assertTrue("Response should contain a list of races", response.getBody().contains("["));
        driver.quit();
    }

    private int extractIdFromResponse(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            if (jsonNode.has("id")) {
                return jsonNode.get("id").asInt();
            } else {
                throw new RuntimeException("Response does not contain 'id': " + responseBody);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract ID from response", e);
        }
    }
}
