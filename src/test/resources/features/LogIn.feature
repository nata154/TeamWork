Feature: LogIn

  Scenario: LogIn with credentials
    When I login user in crypto application
    And I click user account and log out
    Then I check log out - I see password field
    And Close driver