Feature: Test Wikipedia


  Scenario: Test Wikipedia event
    Given User opens Wikipedia page and search all today's events
    Then Calculate the number of articles with Geo-points mentioning 'TodayDate'
    And Forward next day on calender bar
    And Calculate the number of articles with Geo-points mentioning 'TomorrowData'
    Then Compare calculated data

