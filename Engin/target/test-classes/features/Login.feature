Feature: login functionality

  Scenario: login with true credentials
    Given user on homepage
    When  user clicks My Account Link
    And   user clicks Login Link
    Then  Login page should be visible

    When  user fill the login form with the following data
      | username | deneme@deneme.com |
      | password | deneme            |

    And   user clicks Login button
    Then  login should be successfull
