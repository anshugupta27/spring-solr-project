/**
 @author AnshuGupta
 ServiceImplementation class
 */

package com.demo.solr.api.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.demo.solr.api.exception.ResourceNotFoundException;
import com.demo.solr.api.model.Email;
import com.demo.solr.api.propertyValues.PropertyValues;
import com.demo.solr.api.repository.EmailRepository;

@Service
public class ServicesImplemetation implements EmailServices {

	@Autowired
	private EmailRepository repository;
	@Autowired
	PropertyValues propertyValues ;
	
	/**
	 * @return count of all the Email present
	 */
	
	@Override
	public String countAll() {
		String res = "Total number of emails in the dataset are " + String.valueOf(repository.count()) ;
		return res ;
	}
	
	/**
	 * @param date the date when the Email was sent
	 * @return Email list of that particular date
	 * @exception ResourceNotFoundException if the Email list is empty
	 */
	
	@Override
	public List<Email> getByDate(String date , int limit , int offset) throws ResourceNotFoundException {
		Pageable pageable = new OffsetBasedPageRequest(limit, offset);
		

		List<Email> emailsByDate = repository.findAllByDate(date  ,pageable);
		
		if ( emailsByDate.isEmpty())
			throw new ResourceNotFoundException( date) ;
		return emailsByDate ;
		
	}
	
	/**
	 * @exception ResourceNotFoundException if the Email list is empty
	 * @return Email list of all the email
	 */
	
	@Override
	public List<Email> getAllEmails (int limit, int offset) throws ResourceNotFoundException {
        Pageable pageable = new OffsetBasedPageRequest(limit, offset);
        List<Email> emails = repository.findAll(pageable).getContent();
        if ( emails.isEmpty())
        	throw new ResourceNotFoundException("no data") ;
        return emails ;
        
    }
	
	/**
	 * @param from
	 * @return Email list from the particular sender
	 * @exception ResourceNotFoundException if the Email list is empty
	 */
	
	@Override
	public List<Email> getByFrom(String from) throws ResourceNotFoundException {
		
		List<Email> emailsByFrom = repository.findAllByFrom(from);
		try {
			if ( emailsByFrom.isEmpty() ) {
				throw new ResourceNotFoundException (from) ;
			}
		}
		catch(Exception e) {}
		return  emailsByFrom;
	}
	

	
	/**
	 * @param messageId is the messageId
	 * @return Email list of that particular MessageId
	 * @exception ResourceNotFoundException if the Email list is empty
	 */
	
	@Override
	public List<Email> getByMessageId(String messageId) throws ResourceNotFoundException {
		List<Email> emailsByMessageId = repository.findAllByMessageId(messageId);
		
		try {
			if ( emailsByMessageId.isEmpty() ) {
				throw new ResourceNotFoundException (messageId) ;
			}
		}
		catch(Exception e) {}
		return emailsByMessageId ;
	}
	
	
	
	/**
	 * @param id
	 * @return Email of the particular id
	 * @throws ResourceNotFoundException 
	 */

	@Override
	public List<Email> getById ( String id ) throws ResourceNotFoundException {
		Email email =  repository.findByID(id) ;
		List<Email> emailsById = new ArrayList<Email>() ;
		emailsById.add(email) ;
		if ( emailsById.isEmpty())
			throw new ResourceNotFoundException(id) ;
		return emailsById ; 
		
		
	}
	
	/**
	 * @param to
	 * @return Email list of the particular receiver
	 * 
	 */

	@Override
	public List<Email> getByTo(String to)  throws ResourceNotFoundException {
		List<Email> emailsByTo = (List<Email>)repository.findAllByTo(to);
		try {
			if ( emailsByTo.isEmpty() ) {
				throw new ResourceNotFoundException (to) ;
			}
		}
		catch(Exception e) {}
		return emailsByTo ; 
	}
	
	
	
	/**
	 * @param cc
	 * @return Email list of the particular receiver in CC
	 * 
	 */
	
	@Override
	public List<Email> getByCC(String cc) {
		List<Email> emailsByCc = (List<Email>)repository.findAllByCC(cc);
		return emailsByCc ;
	}
	
	/**
	 * @param bcc
	 * @return Email list of the particular receiver in BCC
	 * 
	 */
	
	@Override
	public List<Email> getByBCC(String bcc) {
		List<Email> emailsByBcc = (List<Email>)repository.findAllByBCC(bcc);
		return emailsByBcc ;
	}
	
	/**
	 * @param subject
	 * @return Email list with particular list
	 * 
	 */
	
	@Override
	public List<Email> getBySubject(String subject) {
		List<Email> emailsBySubject = (List<Email>)repository.findAllBySubject(subject);
		return emailsBySubject ;
	}
	
	/**
	 * @param contentType
	 * @return Email list having particular Content
	 * @throws ResourceNotFoundException 
	 */
	
	@Override
	public List<Email> getByContentType(String contentType) {
		List<Email> emailsByContentType = (List<Email>)repository.findAllByContentType(contentType);
		return emailsByContentType ;
	}
	
	
	
	/**
	 * @param body
	 * @return Email list containing particular text
	 * 
	 */
	
	@Override
	public List<Email> getByBody(String body) {
		List<Email> emailsByBody = (List<Email>)repository.findAllByBody(body);
		return emailsByBody ;
	}
	

    /**
     * @param fromData, toDate
     * @return list of emailsBy with particular date range
     */
	
	@Override
	public List<Email> getByDateRange(String fromDate, String toDate) {
		
		ServicesHelper servicesHelper = new ServicesHelper();
		List<String> dateList = servicesHelper.getDateList(fromDate, toDate);
		List<Email> emailsByDateRange = new ArrayList<>();
		int limit = propertyValues.getDateLimit();
		int offset = propertyValues.getDateOffset();
		
		Pageable pageable = new OffsetBasedPageRequest(limit, offset);
		for(String date: dateList) {
			List<Email> tempDateList = repository.findAllByDate(date, pageable);
			emailsByDateRange.addAll(tempDateList);
		}
		return emailsByDateRange;
	}
	

	
	

}
