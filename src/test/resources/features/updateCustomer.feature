Feature: Update Customer

  Scenario: UPDATING THE CUSTOMER NAME

    Given The following customer exists and Admin wants to update the name
      | customerId  | uin  | name         | email             | mobile     | birthdate | doorNumber   | street   |city   | state       | country |zipCode |regionId |
    	| 1  					| 100  | Alfred Novel | alfred@amazon.com | 8569745214 | 1999-05-01| D-101		    | MG Road  |Pune   | Maharashtra | India   |500008  |404  |
    When admin updates the customer name to 'John Sam'
    Then the update is done
