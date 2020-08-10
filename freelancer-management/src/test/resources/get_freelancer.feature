Feature: Should get all freelancers

  Scenario Outline: As an admin, I want to create freelancer
    Given I create a freelancer with first name "<firstName>", last name "<lastName>",  city "<city>" and zipcode "<zipCode>"
    Then I receive status code of 201

    Examples:
      | firstName | lastName | city | zipCode |
      | santosh   | kc       | ktm  | sa12    |

  Scenario Outline: As an Admin, I want to see created freelancer
    Given I requested to see all freelancers.
    Then I should be able to see my newly created freelancer with firstname "<firstName>", last name "<lastName>", city "<city>" and zip code "<zipCode>"

    Examples:
      | firstName | lastName | city | zipCode |
      | santosh   | kc       | ktm  | sa12    |
