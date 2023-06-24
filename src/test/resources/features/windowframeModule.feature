Feature: As a user, I want to be able to go on windows,frames and alerts module and check functionalities
  in given module of DemoQA

  @wip @ui
  Scenario: verify user can locate iframes withing the module
    Given user go to "Alerts, Frame & Windows"
    And user goes to "iframe" within the module
    Then user is successfully locating different WebElements within the module