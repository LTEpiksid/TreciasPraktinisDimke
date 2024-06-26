Feature: Open Swagger and execute API requests

  Scenario: Open Swagger UI from the main page
    Given the application is running
    When I open the main page
    And I click the Swagger button
    Then the Swagger UI should open

  Scenario: Get Racer by ID
    Given the application is running
    When I get the racer with id 1
    Then the response should contain racer details

  Scenario: Get Race by ID
    Given the application is running
    When I get the race with id 1
    Then the response should contain race details

  Scenario: Create and Delete a Status
    Given the application is running
    When I open the main page
    And I create a new status with name "test"
    Then the status should be created successfully
    When I delete the status with name "test"
    Then the status should be deleted successfully

  Scenario: Get Status by ID
    Given the application is running
    When I get the status with id 1
    Then the response should contain status details

  Scenario: Get all Racers
    Given the application is running
    When I get all racers
    Then the response should contain a list of racers

  Scenario: Get all Races
    Given the application is running
    When I get all races
    Then the response should contain a list of races
