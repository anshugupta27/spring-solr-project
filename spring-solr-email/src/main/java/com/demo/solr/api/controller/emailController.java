/**
 * Controller class to process incoming REST API request
 * @author AnshuGupta
 */

package com.demo.solr.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.solr.api.exception.ResourceNotFoundException;
import com.demo.solr.api.model.Email;
import com.demo.solr.api.propertyValues.PropertyValues;
import com.demo.solr.api.services.emailServices;

//@CrossOrigin
@RestController
public class emailController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass()) ;
	@Autowired
	emailServices emailServices;
	
	@Autowired
	PropertyValues propertyValues ;
	/**
	 * @GetMapping annotated methods to handle the HTTP GET request 
	 * and matched with the given URL expression
	 */
	
	
	@GetMapping("/")
	public ResponseEntity <String> homePage() {
		logger.debug("home page");
		return new ResponseEntity<String>("you are on the home page",HttpStatus.ACCEPTED) ;
		
	}
	
	/**
	 * @path /api/v1/getAll
	 * @return List of Emails based on limit and offset
	 * @throws ResourceNotFoundException 
	 */
	
	@GetMapping("/api/v1/getAll")
	public ResponseEntity <List<Email>> getAll(@RequestParam int limit, @RequestParam int offset) throws ResourceNotFoundException {
		logger.debug("Gell All Emails API call");
		List<Email> emailResponse = emailServices.getAllEmails(limit,offset) ;
		return new ResponseEntity<List<Email>> ( emailResponse, HttpStatus.ACCEPTED);
	}
		
	/**
	 * @path /api/v1/getDate/<date>
	 * @param date
	 * @return List of all the Email based on given sent date
	 * @throws ResourceNotFoundException
	 */
	
	@GetMapping("/api/v1/getDate/{date}")
	public ResponseEntity <List<Email>> getEmailsDate(@PathVariable String date) throws ResourceNotFoundException {
		logger.debug("get Sent date API call"  );
		int limit = propertyValues.getLimit();
		int offset = propertyValues.getOffset();
		List<Email> emailResponse = emailServices.getByDate(date,limit,offset) ;
		return new ResponseEntity <List<Email>> (emailResponse,HttpStatus.ACCEPTED);	 
	}
	
    /**
     * 
     * @param fromDate
     * @param toDate
     * @return List of all date in the particular range
     * @throws ResourceNotFoundException
     */
	@GetMapping("/api/v1/getByDateRange")
	public ResponseEntity <List<Email>> getEmailsDateRange(@RequestParam String fromDate, @RequestParam String toDate) throws ResourceNotFoundException {
		logger.debug("Gell By Date Range called");
		List<Email> emailResponse = emailServices.getByDateRange(fromDate, toDate) ;
		return new ResponseEntity<List<Email>> ( emailResponse, HttpStatus.ACCEPTED);
	}
	
	/**
	 * @path /api/v1/getFrom/<from>
	 * @param from
	 * @return List of Email of particular sender
	 * @throws ResourceNotFoundException
	 */
	
	@GetMapping("/api/v1/getFrom/{from}")
	public ResponseEntity <List<Email>> getEmailsFrom(@PathVariable String from) throws ResourceNotFoundException  {
		logger.debug("get From API call");
		int limit = propertyValues.getLimit();
		int offset = propertyValues.getOffset();
		List<Email> emailResponse = emailServices.getByFrom(from,limit,offset) ;
		return new ResponseEntity <List<Email>> (emailResponse,HttpStatus.ACCEPTED) ;
					
		 
	}
	
	/**
	 * @path /api/v1/getTo/<to>
	 * @param to
	 * @return List of Email of particular receiver
	 * @throws ResourceNotFoundException
	 */
	
	@GetMapping("/api/v1/getTo/{to}")
	public ResponseEntity <List<Email>> getEmailsTo(@PathVariable String to) throws ResourceNotFoundException {
		logger.debug("get To API call");
		List<Email> emailResponse = emailServices.getByTo(to) ;
		return new ResponseEntity <List<Email>> (emailResponse,HttpStatus.OK);
	}
	
	/**
	 * @path /api/v1/getById/<Id>
	 * @param Id
	 * @return List of Email of particular ID
	 * @throws ResourceNotFoundException
	 */
	
	@GetMapping("/api/v1/getById/{Id}")
	public ResponseEntity <List<Email>> getEmailsId(@PathVariable String Id) throws ResourceNotFoundException {
		logger.debug("get By ID API call");
		List<Email> emailResponse = emailServices.getById(Id) ;
		return new ResponseEntity <List<Email>> ( emailResponse,HttpStatus.ACCEPTED) ; 
	}
	
	/**
	 * @path /api/v1/getCC/<cc>
	 * @param CC
	 * @return List of Email of particular CC
	 * @throws ResourceNotFoundException
	 */
	
	@GetMapping("/api/v1/getCC/{cc}")
	public ResponseEntity <List<Email>> getEmailsCC(@PathVariable String cc) throws ResourceNotFoundException {
		logger.debug("get CC API call");
		List<Email> emailResponse = emailServices.getByCC(cc) ;
		return new ResponseEntity <List<Email>> (emailResponse,HttpStatus.ACCEPTED);
	}
	
	/**
	 * @path /api/v1/getBCC/<bcc>
	 * @param bcc
	 * @return 
	 * @throws ResourceNotFoundException
	 */
	
	@GetMapping("/api/v1/getBCC/{bcc}")
	public ResponseEntity <List<Email>> getEmailsBCC(@PathVariable String bcc) throws ResourceNotFoundException {
		logger.debug("get BCC API call");
		List<Email> emailResponse = emailServices.getByBCC(bcc) ;
		return new ResponseEntity <List<Email>> (emailResponse,HttpStatus.ACCEPTED);
	}
	
	/**
	 * @path /api/v1/getSubject/<subject>
	 * @param subject
	 * @return
	 * @throws ResourceNotFoundException
	 */
	
	@GetMapping("/api/v1/getSubject/{subject}")
	public ResponseEntity <List<Email>> getEmailsSubject(@PathVariable String subject) throws ResourceNotFoundException {
		logger.debug("get subject API call");
		List<Email> emailResponse = emailServices.getBySubject(subject) ;
		return new ResponseEntity <List<Email>> (emailResponse,HttpStatus.ACCEPTED);
	}
	
	/**
	 * @path /api/v1/getContentType/<contentType>
	 * @param contentType
	 * @return List of Email matching particular content type
	 * @throws ResourceNotFoundException
	 */
	
	@GetMapping("/api/v1/getContentType/{contentType}")
	public ResponseEntity <List<Email>> getEmailsContentType(@PathVariable String contentType) throws ResourceNotFoundException {
		logger.debug("get Content Type API call");
		List<Email> emailResponse = emailServices.getByContentType(contentType) ;
		return new ResponseEntity <List<Email>> ( emailResponse,HttpStatus.ACCEPTED);	 
	}
	
	/**
	 * @path /api/v1/getMimeVersion/<mimeVersion>
	 * @param mimeVersion
	 * @return List of Email matching a particular Mime Version
	 * @throws ResourceNotFoundException
	 */
	
	@GetMapping("/api/v1/getMimeVersion/{mimeVersion}")
	public ResponseEntity <List<Email>> getEmailsMimeVersion(@PathVariable String mimeVersion) throws ResourceNotFoundException {
		logger.debug("get Mime Version API call");
		List<Email> emailResponse = emailServices.getByMimeVersion(mimeVersion) ;
		return new ResponseEntity <List<Email>> (emailResponse,HttpStatus.ACCEPTED);
	}
	
	/**
	 * @path /api/v1/getMessageId/<messageId>
	 * @param messageId
	 * @return List of Email matching a particular Message ID
	 * @throws ResourceNotFoundException
	 */
	
	@GetMapping("/api/v1/getMessageId/{messageId}")
	public ResponseEntity <List<Email>> getEmailsMessageId(@PathVariable String messageId) throws ResourceNotFoundException {
		logger.debug("get Message ID API call");
		List<Email> emailResponse = emailServices.getByMessageId(messageId) ;
		return new ResponseEntity <List<Email>> (emailResponse,HttpStatus.ACCEPTED);
	}
	
	/**
	 * @path /api/v1/getBody/<body>
	 * @param body
	 * @return List of Email matching particular text
	 * @throws ResourceNotFoundException
	 */
	
	@GetMapping("/api/v1/getBody/{body}")
	public ResponseEntity <List<Email>> getEmailsBody(@PathVariable String body) throws ResourceNotFoundException {
		logger.debug("get Text API call");
		List<Email> emailResponse = emailServices.getByBody(body) ;
		return new ResponseEntity <List<Email>> (emailResponse,HttpStatus.ACCEPTED);
	}
	
	/**
	 * @return count of all the Email
	 */
	
	@GetMapping("/countAll")
	public String countAll() {
		logger.debug("count all API call");
		return emailServices.countAll();
	}
}
