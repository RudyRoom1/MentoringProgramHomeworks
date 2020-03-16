Feature: Test Wikipedia


  Scenario: Test Wikipedia event
    Given User opens Wikipedia page and search all today's events
    Then Calculate the number of articles with Geo-points mentioning
    And Forward next day on calender bar
    Then Calculate the number of articles with Geo-points mentioning

