Feature: LogIn

  Scenario: LogIn with credentials
    Given I login user in crypto application
    When I click user account and log out
    Then I check log out - I see password field