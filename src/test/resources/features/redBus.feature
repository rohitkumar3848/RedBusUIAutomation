Feature: Red Bus Seat Booking Module

  Background:
    When user launches redBus website url

  Scenario Outline: Search bus and book first available seat
    Given user is on homepage
    When user enters from "<from>" and selects "<fromSug>"
    And user enters to "<to>" and selects "<toSug>"
    And user selects travel date "<day>" "<month>" "<year>"
    And user searches for buses
    And user selects bus "<busName>"
    Then user selects first available seat successfully

    Examples:
      | from    | fromSug | to        | toSug     | day | month    | year | busName               |
      | Kolkata | Kolkata | Siliguri  | Siliguri | 15  | February | 2026 | Lokenath Bus Service |
#      | Delhi | Delhi | Indore  | Indore | 7  | February | 2026 | Raj Ratan Tours And Travels |
