Feature: Add to Cart1
  Background: : : login with true credentials
    Given user on homepage
    When  user login with username "deneme@deneme.com" and password "deneme"
    Then  login should be successfull
    When  Clean the Cart
    Then  Cart should be empty
    @Test3
    Scenario: Add the product to Cart from 'Wish List' Page
      Given user clicks on "Wish List" header option
      When  user clicks on Add to Cart icon option in the displayed My Wish List page
      And   user clicks on "Shopping Cart" header option
      Then  product should be successfully displayed in the Shopping Cart page
