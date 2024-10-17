Feature: bahn.de homepage

  @TEST_DBS-11
  Scenario: Homepage
    Given I started a new browser session
    When I open the DB Url "https://bahn.de/"
    Then I should be on the DB-homepage "https://www.bahn.de/"
    And  the page title should be "DB Fahrplan, Auskunft, Tickets, informieren und buchen - Deutsche Bahn"
		
