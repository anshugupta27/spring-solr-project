package com.demo.solr.api;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.solr.api.exception.ResourceNotFoundException;
import com.demo.solr.api.model.Email;
import com.demo.solr.api.repository.emailRepository;
import com.demo.solr.api.services.emailServices;

//import com.demo.solr.api.exception.ResourceNotFoundException;
//import com.demo.solr.api.model.Email;
//import com.demo.solr.api.repository.emailRepository;
//import com.demo.solr.api.services.emailServices; 

@SuppressWarnings("deprecation")
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class SpringSolrApplicationTests {

	@Autowired
	private emailServices service;
	
	@MockBean
	private emailRepository repository;
		
	/**
	 * 
	 * @throws ResourceNotFoundException
	 */
	
	
	@Test
	@Order(1)
	public void getAllTest () throws ResourceNotFoundException {
		List<Email> emails = new ArrayList<Email>();
		emails.add(new Email());
		emails.add(new Email());
		
		Page<Email> page = new PageImpl<>(emails);
		when(repository.findAll(Matchers.isA(Pageable.class))).thenReturn(page);
		assertEquals(2, service.getAllEmails(2, 0).size());
	} 
	
	/**
	 * 
	 */
	@Test
	@Order(2)
	public void CountAllTest () {
		List<Email> emails = new ArrayList<Email>();
		emails.add(new Email());
		when(repository.count()).thenReturn((long) emails.size());
		assertEquals("Total number of emails in the dataset are 1", service.countAll());
	} 
	
	@Test
	@Order(3)
	public void GetByMessageIdTest () throws ResourceNotFoundException {
		List<Email> messages = new ArrayList<>();
		Email message = new Email();
		message.setMessageId("1");
		messages.add(message);
		when(repository.findAllByMessageId(Mockito.anyString())).thenReturn(messages);
		Assertions.assertEquals(1, service.getByMessageId("1").size());
		Assertions.assertEquals("1",service.getByMessageId("1").get(0).getMessageId());
	}
	

	@Test
	@Order(4)
	public void GetByDateTest () throws ResourceNotFoundException {
		List<Email> messages = new ArrayList<>();
		Email message = new Email();
		message.setDate("09-07-2021");
		messages.add(message);
		when(repository.findAllByDate(Mockito.anyString(),Matchers.isA(Pageable.class))).thenReturn(messages);
		Assertions.assertEquals(1, service.getByDate("09-07-2021",1,0).size());
		Assertions.assertEquals("09-07-2021",service.getByDate("09-07-2021",1,0).get(0).getDate());
	}

	@Test
	@Order(5)
	public void GetByFromTest () throws ResourceNotFoundException {
		List<Email> messages = new ArrayList<>();
		Email message = new Email();
		message.setFrom("mike@gmail.com");
		messages.add(message);
		when(repository.findAllByFrom(Mockito.anyString(),Matchers.isA(Pageable.class))).thenReturn(messages);
		Assertions.assertEquals(1, service.getByFrom("mike@gmail.com",1,0).size());
		Assertions.assertEquals("mike@gmail.com",service.getByFrom("mike@gmail.com",1,0).get(0).getFrom());
	}

	@Test
	@Order(6)
	public void GetByIdTest () throws ResourceNotFoundException{
		Email message = new Email();
		message.setId("87645398732");
		when(repository.findByID(Mockito.anyString())).thenReturn(message);
		Assertions.assertEquals(1, service.getById("87645398732").size());
		Assertions.assertEquals("87645398732", service.getById("87645398732").get(0).getId());
	}

	@Test
	@Order(7)
	public void GetByCCTest () throws ResourceNotFoundException{
		List<Email> messages = new ArrayList<>();
		Email message = new Email();
		message.setCC("sally@yahoo.in");
		messages.add(message);
		when(repository.findAllByCC(Mockito.anyString())).thenReturn(messages);
		Assertions.assertEquals(1, service.getByCC("sally@yahoo.in").size());
		Assertions.assertEquals("sally@yahoo.in", service.getByCC("sally@yahoo.in").get(0).getCC());
	}

	@Test
	@Order(8)
	public void testGetByBCC () throws ResourceNotFoundException {
		List<Email> messages = new ArrayList<>();
		Email message = new Email();
		message.setBCC("richard@testing.in");
		messages.add(message);
		when(repository.findAllByBCC(Mockito.anyString())).thenReturn(messages);
		Assertions.assertEquals(1, service.getByBCC("richard@testing.in").size());
		Assertions.assertEquals("richard@testing.in", service.getByBCC("richard@testing.in").get(0).getBCC());
	}

	@Test
	@Order(9)
	public void testGetBySubject () throws ResourceNotFoundException {
		List<Email> messages = new ArrayList<>();
		Email message = new Email();
		message.setSubject("This is the test subject");
		messages.add(message);
		when(repository.findAllBySubject(Mockito.anyString())).thenReturn(messages);
		Assertions.assertEquals(1, service.getBySubject("This is the test subject").size());
		Assertions.assertEquals("This is the test subject", service.getBySubject("This is the test subject").get(0).getSubject());
	}

	@Test
	@Order(10)
	public void GetByToTest () throws ResourceNotFoundException {
		List<Email> messages = new ArrayList<>();
		Email message = new Email();
		message.setTo("lisa@google.in");
		messages.add(message);
		when(repository.findAllByTo(Mockito.anyString())).thenReturn(messages);
		Assertions.assertEquals(1, service.getByTo("lisa@google.in").size());
		Assertions.assertEquals("lisa@google.in", service.getByTo("lisa@google.in").get(0).getTo());
	}

	
	
	
	

		
	

} 

