Feature: Create Customer

  Scenario: WITH ALL REQUIRED FIELDS IS SUCCESSFUL

    Given Admin wants to create a customer with the following attributes
      | customerId  | uin  | name         | email             | mobile     | birthdate | city   | state       | country |zipCode |
      | null			  | 100  | Alfred Novel | alfred@amazon.com | 8569745214 | 1999-05-01| Pune   | Maharashtra | India   |500008  |

    When admin saves the new customer 'WITH ALL REQUIRED FIELDS'
    Then the save 'IS SUCCESSFUL'
