Feature: Filter news by coin
  As a user
  I want to get news acording selected coin
  So I can filter news

  Scenario Outline: Filter news by coin
    Given I open News page
    When Sort news by <abbreviationCoin>
    Then Are the news sorted by <abbreviationCoin> 
    
    Examples:
     | abbreviationCoin |
     |      BTC         |
     |      ETH         |
     |      LTC         |
     |      XMR         |
     |      ZEC         |
     |      XRP         |
    