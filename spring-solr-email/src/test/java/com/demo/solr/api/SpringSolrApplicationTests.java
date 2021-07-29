package com.demo.solr.api;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
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

import com.demo.solr.api.exception.ResourceNotFoundException;
import com.demo.solr.api.model.Email;
import com.demo.solr.api.repository.EmailRepository;
import com.demo.solr.api.services.EmailServices;

@SuppressWarnings("deprecation")
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class SpringSolrApplicationTests {

	@Autowired
	private EmailServices service;
	
	@MockBean
	private EmailRepository repository;
		
	/**
	 * 
	 * @throws ResourceNotFoundException
	 */
	
	
	@Test
	@Order(1)
	public void getAllTest () throws ResourceNotFoundException {
		List<Email> Emails = new ArrayList<Email>();
		Emails.add(new Email());
		Emails.add(new Email());
		
		Page<Email> page = new PageImpl<>(Emails);
		when(repository.findAll(Matchers.isA(Pageable.class))).thenReturn(page);
		assertEquals(2, service.getAllEmails(2, 0).size());
	} 
	
	@Test
	@Order(2)
	public void testCountAll () {
		List<Email> emails = new ArrayList<Email>();
		emails.add(new Email());
		emails.add(new Email());
		when(repository.count()).thenReturn((long) emails.size());
		System.out.println(service.countAll());
		Assertions.assertEquals("Total number of emails in the dataset are 2", service.countAll());
	} 
	
	@Test
	@Order(3)
	public void GetByMessageIdTest () throws ResourceNotFoundException {
		List<Email> Emails = new ArrayList<>();
		Email email = new Email();
		email.setMessageId("1");
		Emails.add(email);
		when(repository.findAllByMessageId(Mockito.anyString())).thenReturn(Emails);
		Assertions.assertEquals(1, service.getByMessageId("1").size());
		Assertions.assertEquals("1",service.getByMessageId("1").get(0).getMessageId());
	}
	

	@Test
	@Order(4)
	public void GetByDateTest () throws ResourceNotFoundException {
		List<Email> Emails = new ArrayList<>();
		Email email = new Email();
		email.setDate("09-07-2021");
		Emails.add(email);
		when(repository.findAllByDate(Mockito.anyString(),Matchers.isA(Pageable.class))).thenReturn(Emails);
		Assertions.assertEquals(1, service.getByDate("09-07-2021",1,0).size());
		Assertions.assertEquals("09-07-2021",service.getByDate("09-07-2021",1,0).get(0).getDate());
	}

	@Test
	@Order(5)
	public void GetByFromTest () throws ResourceNotFoundException {
		List<Email> Emails = new ArrayList<>();
		Email email = new Email();
		email.setFrom("mike@gmail.com");
		Emails.add(email);
		when(repository.findAllByFrom(Mockito.anyString())).thenReturn(Emails);
		Assertions.assertEquals(1, service.getByFrom("mike@gmail.com").size());
		Assertions.assertEquals("mike@gmail.com",service.getByFrom("mike@gmail.com").get(0).getFrom());
	}

	@Test
	@Order(6)
	public void GetByIdTest () throws ResourceNotFoundException{
		Email email = new Email();
		email.setId("87645398732");
		when(repository.findByID(Mockito.anyString())).thenReturn(email);
		Assertions.assertEquals(1, service.getById("87645398732").size());
		Assertions.assertEquals("87645398732", service.getById("87645398732").get(0).getId());
	}

	@Test
	@Order(7)
	public void GetByCCTest () {
		List<Email> Emails = new ArrayList<>();
		Email email = new Email();
		email.setCc("sally@yahoo.in");
		Emails.add(email);
		when(repository.findAllByCC(Mockito.anyString())).thenReturn(Emails);
		Assertions.assertEquals(1, service.getByCC("sally@yahoo.in").size());
		Assertions.assertEquals("sally@yahoo.in", service.getByCC("sally@yahoo.in").get(0).getCc());
	}

	@Test
	@Order(8)
	public void testGetByBCC () {
		List<Email> Emails = new ArrayList<>();
		Email email = new Email();
		email.setBcc("richard@testing.in");
		Emails.add(email);
		when(repository.findAllByBCC(Mockito.anyString())).thenReturn(Emails);
		Assertions.assertEquals(1, service.getByBCC("richard@testing.in").size());
		Assertions.assertEquals("richard@testing.in", service.getByBCC("richard@testing.in").get(0).getBcc());
	}

	@Test
	@Order(9)
	public void testGetBySubject () throws ResourceNotFoundException {
		List<Email> Emails = new ArrayList<>();
		Email email = new Email();
		email.setSubject("This is the test subject");
		Emails.add(email);
		when(repository.findAllBySubject(Mockito.anyString())).thenReturn(Emails);
		Assertions.assertEquals(1, service.getBySubject("This is the test subject").size());
		Assertions.assertEquals("This is the test subject", service.getBySubject("This is the test subject").get(0).getSubject());
	}

	@Test
	@Order(10)
	public void GetByToTest () throws ResourceNotFoundException  {
		List<Email> Emails = new ArrayList<>();
		Email email = new Email();
		email.setTo("lisa@google.in");
		Emails.add(email);
		when(repository.findAllByTo(Mockito.anyString())).thenReturn(Emails);
		Assertions.assertEquals(1, service.getByTo("lisa@google.in").size());
		Assertions.assertEquals("lisa@google.in", service.getByTo("lisa@google.in").get(0).getTo());
	}
	
	@Test
	@Order(11)
	public void getByContentTypeTest ()  {
		List<Email> Emails = new ArrayList<>();
		Email email = new Email();
		email.setContentType("test");
		Emails.add(email);
		Mockito.when(repository.findAllByContentType(Mockito.anyString())).thenReturn(Emails);
		Assertions.assertEquals(1, service.getByContentType("test").size());
		Assertions.assertEquals("test", service.getByContentType("test").get(0).getContentType());
	}
	
	@Test
	@Order(12)
	public void getByBodyTest ()  {
		List<Email> Emails = new ArrayList<>();
		Email email = new Email();
		email.setBody("test body");
		Emails.add(email);
		Mockito.when(repository.findAllByBody(Mockito.anyString())).thenReturn(Emails);
		Assertions.assertEquals(1, service.getByBody("test body").size());
		Assertions.assertEquals("test body", service.getByBody("test body").get(0).getBody());
	}
	
	
	@Test
	@Order(13)
	public void getByDateRangeTest () throws  ParseException {
		List<Email> Emails = new ArrayList<>();
		Email email = new Email();
		email.setDate("16-06-2021");
		Emails.add(email);
		Page<Email> page = new PageImpl<>(Emails);
		Mockito.when(this.repository.findAll(Matchers.isA(Pageable.class))).thenReturn(page);
		Assertions.assertEquals(0, service.getByDateRange("15-06-2021","17-06-2021").size());
	}

	
	@Test
	@Order(15)
	public void getToEmptyTest () throws ResourceNotFoundException {
		List<Email> Emails = new ArrayList<>();
		Mockito.when(repository.findAllByTo(Mockito.anyString())).thenReturn(Emails);
		Assertions.assertEquals(0, service.getByTo("test@google.com").size());
	}
	@Test
	@Order(16)
	public void getByMessageIdEmptyTest () throws ResourceNotFoundException {
		List<Email> Emails = new ArrayList<>();
		Mockito.when(repository.findAllByMessageId(Mockito.anyString())).thenReturn(Emails);
		Assertions.assertEquals(0, service.getByMessageId("9874").size());
	}
	@Test
	@Order(17)
	public void getByFromEmptyTest () throws ResourceNotFoundException {
		List<Email> Emails = new ArrayList<>();
		Mockito.when(repository.findAllByFrom(Mockito.anyString())).thenReturn(Emails);
		Assertions.assertEquals(0, service.getByFrom("test@google.com").size());
	}
	
	
	

		
	

} 

