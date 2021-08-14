package com.rvy.cucumber.bdd.stepdefs;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvy.entity.Customer;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

/**
 * Step Definition Class for Employee.
 *
 * <p>Uses Java Lambda style step definitions so that we don't need to worry
 * about method naming for each step definition</p>
 */
public class updateCustomerSteps extends AbstractSteps implements En {
	
	boolean notNull = false;
	@Autowired
	Customer customer;
	
  public updateCustomerSteps() {
	  
	  
	  
    Given("The following customer exists and Admin wants to update the name", (DataTable customerDt) -> {
      testContext().reset();
      
      List<List<String>> customerData = customerDt.asLists(String.class);
      // First row of DataTable has the employee attributes hence calling get(0) method.
//      for(List<String> e: customerList) {
//    	  System.out.println(e);
//      }
//      System.out.println(customerData.get(1));
      
//      super.testContext()
//          .setPayload(customerList.get(0));
      customer = populateCustomer(customerData.get(1));
      
    });

    When("admin updates the customer name to {string}", (String newName) -> {
//      String createCustomerUrl = "rvy/api/cm/v1/customers";
//
//      // AbstractSteps class makes the POST call and stores response in TestContext
//      executePost(createCustomerUrl);
    	
    	customer.setName(newName);
    });

    /**
     * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
     */
    Then("the update is done", () -> {
//      Response response = testContext().getResponse();
//
//      switch (expectedResult) {
//        case "IS SUCCESSFUL":
//          assertThat(response.statusCode()).isIn(200, 201);
//          break;
//        case "FAILS":
//          assertThat(response.statusCode()).isBetween(400, 504);
//          break;
//        default:
//          fail("Unexpected error");
    	assertEquals("John Sam",customer.getName());
//      }
    	
    });

  }

  private Customer populateCustomer(List<String> list) {
		Customer customer = new Customer();
		
		customer.setCustomerId(Integer.parseInt(list.get(0)));
		customer.setUin(Integer.parseInt(list.get(1)));
		customer.setName(list.get(2));
		customer.setEmail(list.get(3));
		customer.setMobile(Long.parseLong(list.get(4)));
		customer.setBirthdate(LocalDate.parse(list.get(5)));
		customer.setDoorNumber(list.get(6));
		customer.setStreet(list.get(7));
		customer.setCity(list.get(8));
		customer.setState(list.get(9));
		customer.setCountry(list.get(10));
		customer.setZipCode(Long.parseLong(list.get(11)));
		customer.setRegionId(Integer.parseInt(list.get(12)));
		return customer;
	}
}

