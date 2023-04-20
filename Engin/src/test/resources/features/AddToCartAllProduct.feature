Feature: Products add to Cart
  @Test2
  Scenario Outline: Products
    Given user on homepage
    And  user login with username "deneme@deneme.com" and password "deneme"
    Then  login should be successfull
    When  Clean the Cart
    Then  Cart should be empty
    When  user clicks Desktops button
    And   user clicks "<asideMenu>"
    And   user add all products to the Cart
    Then  all listed products should be listed in Cart



    Examples: menu

      | asideMenu           |
      | Mac                 |
      | Laptops & Notebooks |
      | Components          |
      | Tablets             |
      | Phones & PDAs       |
      | Cameras             |
      | MP3 Players         |