Feature: Login test

  @Excel
  Scenario: Login test with excel data
    Given user on homepage
    When  user try to login with credential given in excel file name as "excelUsers.xlsx"
    Then  login should be as in excel file