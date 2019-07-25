Feature: LogIn

  Background:
    Given I login user in crypto application

  Scenario: LogIn with credentials
    When I click user account and log out
    Then I check log out - I see password field