@login2
Feature: login functionality

  Scenario Outline: login 2
    Given user on homepage
    When  user clicks the following links with text
      | My Account |
      | Login      |
    Then Login page should be visible

    When  user fill the login form with the following data
      | username | <username> |
      | password | <password> |

    And   user clicks Login button
    Then  login should be "<success>"

    Examples:
      | username           | password | success |
      | deneme@deneme.com  | deneme   | true    |
      |                    |          | false   |
      | deneme@deneme.com  |          | false   |
      |                    | deneme   | false   |
      | deneme1@deneme.com | deneme   | false   |
      | deneme@deneme.com  | deneme1  | false   |
      | deneme@deneme.com  | deneme   | true    |
      | a                  | a        | false   |








