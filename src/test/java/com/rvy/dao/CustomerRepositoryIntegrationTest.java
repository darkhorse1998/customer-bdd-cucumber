package com.rvy.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rvy.CustomerSupermarketAppApplication;
import com.rvy.entity.Customer;
import com.rvy.repository.CustomerRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CustomerSupermarketAppApplication.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class CustomerRepositoryIntegrationTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private CustomerRepository repository;

	
	@Test
	public void insertCheckSize() {
		
		Integer _prevSz = repository.findAll().size();
		Customer customer1 = new Customer(null,17080,"Andhera kayam","ak@gmail.com",9777172228L,LocalDate.of(2009, 1, 1),"Door 9","Street 7","Pink City","Rajasthan","India",769007L,1214);
		Customer customer2 = new Customer(null,18080,"Mogambo khush","mk@gmail.com","Citylife");
		Customer customer3 = new Customer(null,19080,"Shakal Welcme","sw@gmail.com","Magarmachhcity");
		
		int ctr=0;
		entityManager.persistAndFlush(customer1);++ctr;
		entityManager.persistAndFlush(customer2);++ctr;
		entityManager.persistAndFlush(customer3);++ctr;
		List<Customer> customerList = repository.findAll();
		
		assertThat(customerList).hasSize(_prevSz+ctr);
		
	}
	
	@Test
	public void checkingIds() {
		
		Customer customer1 = new Customer(null,19080,"Shakal Welcme","sw@gmail.com","Magarmachhcity");
		entityManager.persistAndFlush(customer1);
		
		Customer customerFromDb = repository.findById(customer1.getCustomerId()).get();
		assertThat(customerFromDb).isEqualTo(customer1);
	}
	
	@Test
	public void checkingDaoFunctions()
	{
		Customer customer = new Customer(null,19080,"Shakal Welcome","sw@gmail.com","Magarmachhcity");
		entityManager.persistAndFlush(customer);
		
		Customer custDb = repository.findByName("Shakal Welcome");
		assertThat(custDb.getEmail()).isEqualTo(customer.getEmail());
		assertThat(customer.getCity()).isEqualTo(custDb.getCity());
	}
	
	
    @Test
    public void whenInvalidContactDetails_thenReturnNull() {
    		Customer cust = repository.findByName("Trojan Virus");
    		List<Customer> custList = new ArrayList<>();
    		if(cust!=null)custList.add(cust);
    		assertThat(custList).isEmpty();
    }
    

    @Test
    public void whenInvalidId_thenReturnNull() {
    	Customer fromDb = repository.findById(-11).orElse(null);
        assertThat(fromDb).isNull();
    }
    
    


}