Feature: UpdatingUserAccount
  As a authorized user
  I want update my first name and surname
  So I can change profile data

  @tag1
  Scenario: Changing user account
    Given I login user
    When I go to User Account profile
    And Change And save FirstName , Surname in User Account
    Then I check updating User Account

   