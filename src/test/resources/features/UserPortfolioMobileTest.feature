Feature: UserPortfolioMobileTest

  Background:
    Given I login user in crypto application

  Scenario:
    When I create new portfolio
    And I change portfolio name
    Then I check portfolio name
    And I delete current portfolio
    Then I check deleting of portfolio with such name
    And I click user account and log out
