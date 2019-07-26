Feature: UserPortfolio
  As a user
  I want to create, edit and delete portfolio
  So I can work with portfolio

  Scenario: Work with portfolio
    Given I login user
    When Create User Portfolio
    Then Is Portfolio present
    And Change User Portfolio name
    Then Is Portfolio with changed name present
    And Delete User Portfolio
    Then Is Portfolio delete