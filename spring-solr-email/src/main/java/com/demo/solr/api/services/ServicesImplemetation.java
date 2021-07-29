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
import com.demo.solr.api.repository.emailRepository;

@Service
public class ServicesImplemetation implements emailServices {

	@Autowired
	private emailRepository repository;
	
	
	/**
	 * @return count of all the Email present
	 */
	
	@Override
	public String countAll() {
		return "Total number of emails in the dataset are " + String.valueOf(repository.count());
	}
	
	/**
	 * @param date the date when the Email was sent
	 * @return Email list of that particular date
	 * @exception ResourceNotFoundException if the Email list is empty
	 */
	
	@Override
	public List<Email> getByDate(String date , int limit , int offset) throws ResourceNotFoundException {
		Pageable pageable = new OffsetBasedPageRequest(limit, offset);
		

		List<Email> Emails = repository.findAllByDate(date  ,pageable);
		
		if ( Emails.isEmpty())
			throw new ResourceNotFoundException( date) ;
		return Emails ;
		
	}
	
	/**
	 * @exception ResourceNotFoundException if the Email list is empty
	 * @return Email list of all the email
	 */
	
	@Override
	public List<Email> getAllEmails (int limit, int offset) throws ResourceNotFoundException {
        Pageable pageable = new OffsetBasedPageRequest(limit, offset);
        List<Email> Emails = repository.findAll(pageable).getContent();
        if ( Emails.isEmpty())
        	throw new ResourceNotFoundException("no data") ;
        return Emails ;
        
    }
	
	/**
	 * @param from
	 * @return Email list from the particular sender
	 * @exception ResourceNotFoundException if the Email list is empty
	 */
	
	@Override
	public List<Email> getByFrom(String from) throws ResourceNotFoundException {
		
		List<Email> Emails = repository.findAllByFrom(from);
		try {
			if ( Emails.isEmpty() ) {
				throw new ResourceNotFoundException (from) ;
			}
		}
		catch(Exception e) {}
		return  Emails;
	}
	

	
	/**
	 * @param messageId is the messageId
	 * @return Email list of that particular MessageId
	 * @exception ResourceNotFoundException if the Email list is empty
	 */
	
	@Override
	public List<Email> getByMessageId(String messageId) throws ResourceNotFoundException {
		List<Email> Emails = repository.findAllByMessageId(messageId);
		
		try {
			if ( Emails.isEmpty() ) {
				throw new ResourceNotFoundException (messageId) ;
			}
		}
		catch(Exception e) {}
		return Emails ;
	}
	
	
	
	/**
	 * @param id
	 * @return Email of the particular id
	 * @throws ResourceNotFoundException 
	 */

	@Override
	public List<Email> getById ( String id ) throws ResourceNotFoundException {
		Email email =  repository.findByID(id) ;
		List<Email> emails = new ArrayList<Email>() ;
		emails.add(email) ;
		if ( emails.isEmpty())
			throw new ResourceNotFoundException(id) ;
		return emails ; 
		
		
	}
	
	/**
	 * @param to
	 * @return Email list of the particular receiver
	 * 
	 */

	@Override
	public List<Email> getByTo(String to)  throws ResourceNotFoundException {
		List<Email> Emails = (List<Email>)repository.findAllByTo(to);
		try {
			if ( Emails.isEmpty() ) {
				throw new ResourceNotFoundException (to) ;
			}
		}
		catch(Exception e) {}
		return Emails ; 
	}
	
	
	
	/**
	 * @param cc
	 * @return Email list of the particular receiver in CC
	 * 
	 */
	
	@Override
	public List<Email> getByCC(String cc) {
		List<Email> Emails = (List<Email>)repository.findAllByCC(cc);
		return Emails ;
	}
	
	/**
	 * @param bcc
	 * @return Email list of the particular receiver in BCC
	 * 
	 */
	
	@Override
	public List<Email> getByBCC(String bcc) {
		List<Email> Emails = (List<Email>)repository.findAllByBCC(bcc);
		return Emails ;
	}
	
	/**
	 * @param subject
	 * @return Email list with particular list
	 * 
	 */
	
	@Override
	public List<Email> getBySubject(String subject) {
		List<Email> Emails = (List<Email>)repository.findAllBySubject(subject);
		return Emails ;
	}
	
	/**
	 * @param contentType
	 * @return Email list having particular Content
	 * @throws ResourceNotFoundException 
	 */
	
	@Override
	public List<Email> getByContentType(String contentType) {
		List<Email> Emails = (List<Email>)repository.findAllByContentType(contentType);
		return Emails ;
	}
	
	
	
	/**
	 * @param body
	 * @return Email list containing particular text
	 * 
	 */
	
	@Override
	public List<Email> getByBody(String body) {
		List<Email> Emails = (List<Email>)repository.findAllByBody(body);
		return Emails ;
	}
	

    /**
     * @param fromData, toDate
     * @return list of emails with particular date range
     */
	
	@Override
	public List<Email> getByDateRange(String fromDate, String toDate) {
		
		ServicesHelper servicesHelper = new ServicesHelper();
		List<String> dateList = servicesHelper.getDateList(fromDate, toDate);
		for ( String date : dateList)
		{
			System.out.println(date);
		}
		List<Email> Emails = new ArrayList<>();
		int limit = 10;
		int offset = 0;
		Pageable pageable = new OffsetBasedPageRequest(limit, offset);
		for(String date: dateList) {
			List<Email> tempDateList = repository.findAllByDate(date, pageable);
			Emails.addAll(tempDateList);
		}
		return Emails;
	}
	

	
	

}
