Feature: Create Customer

  Scenario: WITH ALL REQUIRED FIELDS IS SUCCESSFUL

    Given Admin wants to create a customer with the following attributes
      | uin  | name        | email             | mobile     | birthdate | city   | state       | country | 
      | 100 | Alfred Novel | alfred@amazon.com | 8569745214 | 01-05-1999| Pune   | Maharashtra | India   |  

    When admin saves the new customer 'WITH ALL REQUIRED FIELDS'
    Then the save 'IS SUCCESSFUL'
