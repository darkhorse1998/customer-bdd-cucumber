Feature: Find Customer

  Scenario: FINDING CUSTOMER BY ID

    Given The following customer exists and Admin wants to find the customer by ID
      | customerId  | uin  | name         | email             | mobile     | birthdate | doorNumber   | street   |city   | state       | country |zipCode |regionId |
    	| 1  					| 100  | Alfred Novel | alfred@amazon.com | 8569745214 | 1999-05-01| D-101		    | MG Road  |Pune   | Maharashtra | India   |500008  |404  |
    	| 2  					| 101  | Bell Novel | bell@amazon.com | 9598745214 | 1999-08-01| C-102		    | GM Road  |Nagpur   | Maharashtra | India   |500078  |504  |
    When admin wants to find the customer with ID '2'
    Then the customer is found
    

