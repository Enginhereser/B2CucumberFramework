Feature: Cart functionality

  Background: : : login with true credentials
    Given user on homepage
    When  user login with username "deneme@deneme.com" and password "deneme"
    Then  login should be successfull
    When  Clean the Cart
    Then  Cart should be empty


  Scenario: add a product
    Given user search for "iMac"
    When  user add "iMac" to the Cart
    Then  success notification with "iMac" should be visible
    And   product "iMac" should be listed in Cart

  @Test1
  Scenario: add more products
    Given user search for "mac"
    When  user add all products to the Cart
    And   save products names into "products.txt"
    Then  all listed products should be listed in Cart


  Scenario Outline: add more products
    Given user search for "<name>"
    When  user add "<name>" to the Cart
    And   user change navigate to Cart
    Then  product "<name>" should be listed in Cart
    And   user change the quantity of "<name>" as <quantity>
    Then  quantity should be updated
    Examples:
      | name      | quantity |
      | imac      | 2        |
      | iPhone    | 2        |
      | iPod Nano | 3        |