@WishList
Feature: wishlist functionality


  Background: login with true credentials
    Given user on homepage
    When  user login with username "deneme@deneme.com" and password "deneme"
    Then  login should be successfull


  Scenario: wishlist TC 1
    Given user search for "iMac"
    When  user add "iMac" to the wishlist
    Then  success notification with "wish list!" should be visible

  Scenario: wishlist TC 2
    Given user search for "iMac"
    When  user add "iMac" to the wishlist
    Then  success notification with "wish list!" should be visible

