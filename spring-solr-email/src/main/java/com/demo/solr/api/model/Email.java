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


//@Data
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
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
	
	
	/**
	 * @constructor Email default constructor
	 */
	
public Email() {};
	
	/**
	 * @constructor Email parameterized constructor
	 * @param date
	 * @param from
	 * @param to
	 * @param cc
	 * @param contentType
	 * @param subject
	 * @param mimeVersion
	 * @param messsageId
	 * @param body
	 * @param id
	 * 
	 */
	public Email(String date, String from, String to, String cc, String contentType,
			     String subject,float mimeVersion, String messageId, String body, String id) {
		this.date = date;
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.contentType = contentType;
		this.subject = subject;
		this.mimeVersion = mimeVersion;
		this.messageId = messageId;
		this.body = body;
		this.id = id;
	}

	/**
	 * @return string convert the received object into string
	 */
	
//	@Override
//	public String toString() {
//		return "Email [Sentdate=" + date + ", From=" + from + ", To=" + to + ", CC=" + cc + ", Content_Type="
//				+ contentType + ", Subject=" + subject + ", Mime_Version=" + mimeVersion + ", Message_ID="
//				+ messageId + ", Text=" + body + "]";
//	}
	
	/**
	 * get the sent date
	 * @return String of sent date
	 */
	
	public String getDate() {
		return date;
	}
	
	/**
	 * set the sent date
	 * @param date
	 */
	
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * get the sender
	 * @return from
	 */
	
	public String getFrom() {
		return from;
	}
	
	/**
	 * set the sender
	 * @param from
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	/**
	 * get the receiver
	 * @return to
	 */
	
	public String getTo() {
		return to;
	}
	
	/**
	 * set the receiver
	 * @param to
	 */
	
	public void setTo(String to) {
		this.to = to;
	}
	
	/**
	 * get cc
	 * @return cc
	 */
	
	public String getCC() {
		return cc;
	}
	
	/**
	 * set cc
	 * @param cc
	 */
	
	public void setCC(String cc) {
		this.cc = cc;
	}
	
    /**
     * @return bcc
     */
	public String getBCC() {
		return bcc;
	}
	
	/**
	 * @param bcc
	 */
	public void setBCC(String bcc) {
		this.bcc = bcc ;
	}
	/**
	 * get the content type
	 * @return contentType
	 */
	
	public String getContentType() {
		return contentType;
	}
	
	/**
	 * set the content type
	 * @param contentType
	 */
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	/**
	 * get the subject
	 * @return subject
	 */
	
	public String getSubject() {
		return subject;
	}
	
	/**
	 * set the subject
	 * @param subject
	 */
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * get the mime version
	 * @return mimeVersion
	 */
	
	public float getMimeVersion() {
		return mimeVersion;
	}
	
	/**
	 * set the mime version
	 * @param mimeVersion
	 */
	
	public void setMimeVersion(float mimeVersion) {
		this.mimeVersion = mimeVersion;
	}
	
	/**
	 * get the message ID
	 * @return messageId
	 */
	
	public String getMessageId() {
		return messageId;
	}
	
	/**
	 * set the message ID
	 * @param messageId
	 */
	
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
	/**
	 * get the body
	 * @return body
	 */
	
	public String getBody() {
		return body;
	}
	
	/**
	 * set the body
	 * @param body
	 */
	
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * get the id
	 * @return id
	 */
	
	public String getId() {
		return id;
	}
	
	/**
	 * set the id
	 * @param id
	 */
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	

}
