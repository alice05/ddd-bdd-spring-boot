Feature: The freelancer can be created
  Scenario: User makes call to POST /freelancer/create
    When the user calls freelancer slash create
    Then the user receives status code of 201
    And the user receives success message