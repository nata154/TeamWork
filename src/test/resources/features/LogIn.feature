Feature: LogIn

  Background:
    Given Get driver

  Scenario: LogIn with credentials
    When I login user in crypto application
    When I click user account and log out
    Then I check log out - I see password field
    Then Close driver