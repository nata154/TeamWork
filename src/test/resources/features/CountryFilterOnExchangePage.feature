Feature: Country filter on exchange page
  As a user
  I want to get exchanges acording selected country
  So I can filter exchanges

  Scenario Outline: Filter exchanges by country(labels)
    Given I open Exchange page
    When Filter news by <country>
    Then Is number of results equals number from <country> label
    
    Examples:
      | country        |
      | China          |      
      | United Kingdom |
      | Russia         |
      | Denmark        |
      | Japan          |
      | Singapore      |
      | Brazil         |
      | Unknown        |   
    
  Scenario Outline: Filter exchanges by country(badges)
    Given I open Exchange page
    When Filter news by <country>
    Then Is number of results equals number from <country> badge
    
    Examples:
      | country        |
      | China          |      
      | United Kingdom |
      | Russia         |
      | Denmark        |
      | Japan          |
      | Singapore      |
      | Brazil         |
      | Unknown        |          