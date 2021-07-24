/**
 * @author AnshuGupta
 * Email Service Class
 */

package com.demo.solr.api.services;

import java.util.List;
import com.demo.solr.api.exception.ResourceNotFoundException;
import com.demo.solr.api.model.Email;

/**
 * To do loose coupling so that we can change the implementation any time without having any effect on the REST API
 *
 */

public interface emailServices {

	String countAll();
	List<Email> getByDate(String date , int limit, int offset) throws ResourceNotFoundException ;
	List<Email> getByFrom(String from, int limit, int offset) throws ResourceNotFoundException;
	List<Email> getByTo(String to) throws ResourceNotFoundException;
	List<Email> getByCC(String cc) throws ResourceNotFoundException;
	List<Email> getByBCC(String bcc) throws ResourceNotFoundException;
	List<Email> getBySubject(String subject) throws ResourceNotFoundException;
	List<Email> getByContentType(String contentType) throws ResourceNotFoundException;
	List<Email> getByMimeVersion(String mimeVersion) throws ResourceNotFoundException;
	List<Email> getByMessageId(String messageId) throws ResourceNotFoundException;
	List<Email> getByBody(String text) throws ResourceNotFoundException;
	List<Email> getAllEmails(int limit, int offset) throws ResourceNotFoundException;
	List<Email> getById (String id) throws ResourceNotFoundException;
	List<Email> getByDateRange(String fromDate, String toDate) throws ResourceNotFoundException;

	
}
