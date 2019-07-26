Feature: Coin in user portfolio
  As a user
  I want to add, edit and delete coin in portfolio
  So I can work with coin in portfolio

  Scenario: Work with portfolio
    Given I login user
    When Create User Portfolio
    And Add Coin to user portfolio
    Then Is Coin added
    And Change amount of coins
    Then Is amount of coins changed
    And Delete coin from user portfolio
    Then Is coin deleted