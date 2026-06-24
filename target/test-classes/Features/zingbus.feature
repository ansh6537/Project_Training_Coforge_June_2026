@zingbus
Feature: ZingBus Search

  Scenario: Search bus from Delhi to Jaipur
    Given user is on zingbus homepage
    When user enters from city "Delhi"
    And user enters to city "Jaipur"
	# And user select date "June 28, 2026"
    Then user clicks on search button
    
    And user applies cheapest filter
	# And user applies sleeper filter
    Then user should see filtered bus results
