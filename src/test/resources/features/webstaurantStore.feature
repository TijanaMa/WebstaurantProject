@wip
Feature: Webstaurant Store task
  TC 1
  User should be able to search for specific product, all products should contain given word in its title,
  then user should be able to add last of found items to Cart, and then empty the Cart

  Scenario: Search product, add last item to Cart and empty the Cart
    Given user is on the Webstaurant Store website
    When user search for 'stainless work table'
    Then user should see search results has word 'Table' in the title
    When user add the last item to the cart
    Then user empty the cart and the cart should be empty

