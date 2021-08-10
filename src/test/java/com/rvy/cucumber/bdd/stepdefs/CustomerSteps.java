package com.rvy.cucumber.bdd.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.rvy.entity.Customer;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import java.util.List;

/**
 * Step Definition Class for Employee.
 *
 * <p>Uses Java Lambda style step definitions so that we don't need to worry
 * about method naming for each step definition</p>
 */
public class CustomerSteps extends AbstractSteps implements En {

  public CustomerSteps() {

    Given("Admin wants to create a customer with the following attributes", (DataTable customerDt) -> {
      testContext().reset();
      List<List<Customer>> customerList = customerDt.asList(Customer.class);

      // First row of DataTable has the employee attributes hence calling get(0) method.
      super.testContext()
          .setPayload(customerList.get(0));
    });

//    And("with the following phone numbers", (DataTable phoneDt) -> {
//      List<Phone> phoneList = phoneDt.asList(Phone.class);
//      super.testContext()
//          .getPayload(Employee.class)
//          .setPhones(phoneList);
//    });

    When("admin saves the new customer {string}", (String testContext) -> {
      String createCustomerUrl = "rvy/api/cm/v1/customers";

      // AbstractSteps class makes the POST call and stores response in TestContext
      executePost(createCustomerUrl);
    });

    /**
     * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
     */
    Then("the save {string}", (String expectedResult) -> {
      Response response = testContext().getResponse();

      switch (expectedResult) {
        case "IS SUCCESSFUL":
          assertThat(response.statusCode()).isIn(200, 201);
          break;
        case "FAILS":
          assertThat(response.statusCode()).isBetween(400, 504);
          break;
        default:
          fail("Unexpected error");
      }
    });

  }
}
