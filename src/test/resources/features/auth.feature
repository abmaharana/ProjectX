@smoke
Feature: User Authentication

  Scenario: User signs up successfully
    Given I open the browser
    When I navigate to the signup page
    And I sign up with username "testuser" and password "password123"


  Scenario: User logs in successfully
    Given I open the browser
    When I navigate to the login page
    And I login with username "testuser" and password "password123"
    Then I should see the dashboard with username "testuser"