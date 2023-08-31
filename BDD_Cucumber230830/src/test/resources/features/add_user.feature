Feature: Add User
  Scenario: Adding a new user
    Given I am on the main page
    When I enter the user name "John Doe"
    And I click the "ADD" button
    Then I should see the response "User added successfully"