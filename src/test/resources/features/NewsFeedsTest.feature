Feature: NewsFeedsMobileTest

  Background:
    Given I login user in crypto application

  Scenario Outline: Check news filtered by feeds
    When I go to the sources page from the news page
    Then I choose a concrete <feed> item and click it
    Then I go to the filtered news page
    And I check how were news filtered by <feed>

    Examples:
      | feed             |
      | CryptoGlobe      |
      | CoinDesk         |
      | Bitcoin Magazine |
      | CCN              |
      | Bitcoinist       |