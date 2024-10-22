Feature: Homepage Meta-Nav-Menu test

  Background:
		#@PRECOND_DBS-12
    Given I'm on the "https://www.bahn.de/" homepage
	    #@PRECOND_DBS-13
    Given I close the cookies banner

  Scenario Outline: Meta-Nav-Menu functionality test
    When I click on a menu "<item>"
    Then I should be directed to the correct address "<address>"
    Examples:
      | item            | address                          |
      | Privatreisen    | https://www.bahn.de/             |
      | Geschäftsreisen | https://www.bahn.de/bahnbusiness |
