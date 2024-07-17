Feature: WebTables Automation

  Scenario: Add a user and validate the user has been added to the table
    Given I navigate to the web tables page
    When I add a user with first name "John", last name "Doe", username "johndoe", password "password123", customer "Company AAA", role "Customer", email "john.doe@example.com", and phone "1234567890"
    Then I should see the user "johndoe" in the table

  Scenario: Delete a user from the table and validate the user has been deleted
    Given I navigate to the web tables page
    When I delete the user "novak"
    Then I should not see the user "novak" in the table
