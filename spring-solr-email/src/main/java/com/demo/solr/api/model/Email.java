/**
 * @author AnshuGupta
 * Email Object Model class
 */

package com.demo.solr.api.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "dataset") // name of solr core (dataset) 
public class Email {	
	
	@Id
	private String id;
	@Field
	private String date;
	@Field
	private String from;
	@Field
	private String to;
	@Field
	private String cc;
	@Field
	private String bcc;
	@Field
	private String contentType;
	@Field
	private String subject;
	@Field
	private float mimeVersion;
	@Field
	private String messageId;
	@Field
	private String body;
	
	
}
