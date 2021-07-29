/**
 * @author AnshuGupta
 * Email Repository class
 */

package com.demo.solr.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.solr.api.model.Email;

@Repository
public interface EmailRepository extends SolrCrudRepository<Email, String> {

	@Query("date:?0")
	List<Email> findAllByDate(String date, Pageable pageable);
	@Query("from:?0")
	List<Email> findAllByFrom(String from);
	@Query("to:?0")
	Iterable<Email> findAllByTo(String to);
	@Query("cc:?0")
	Iterable<Email> findAllByCC(String cc);
	@Query("bcc:?0")
	Iterable<Email> findAllByBCC(String bcc);
	@Query("contentType:?0")
	Iterable<Email> findAllByContentType(String contentType);
	@Query("subject:?0")
	Iterable<Email> findAllBySubject(String subject);
	@Query("messageId:?0")
	List<Email> findAllByMessageId(String messageId);
	@Query("body:?0")
	Iterable<Email> findAllByBody(String body);
	@Query("id:?0")
	Email findByID ( String id ) ;
	
	
}
