@wip
Feature: Webstaurant Store task
  TC 1
  User should be able to search for specific product, all products should contain given word in its title,
  then user should be able to add last of found items to Cart, and then empty the Cart

  Scenario: Search product, add last item to Cart and empty the Cart
    Given user is on the Webstaurant Store website
    When user search for 'stainless work table'
#    Then user should see search results has word 'Table' in the title
    When user add the last item to the cart
    And user empty the cart
    Then the cart should be empty

#  We could implement Scenario Outline  (locators are different for different view)
#  In this case I didn't use it, I just went with default Grid view
#    When user search for 'stainless work table' in <View> mode
#    Examples:
#      | View       |
#      | Grid View  |
#      | List View  |
#  --> then I would use different locators for WebElement title