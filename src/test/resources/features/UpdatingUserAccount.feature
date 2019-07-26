Feature: UpdatingUserAccount
  As a authorized user
  I want update my first name and surname
  So I can change profile data


  Scenario: Changing user account
    Given I login user
    When I go to User Account profile
    And Change And save First name and Surname in User Account
    Then I check updating User Account