package com.demo.solr.api.propertyValues;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class is made to fetch values of limit and offset from application.properties file
 * 
 *
 */
@Component
public class PropertyValues {

	@Value("${pageable.values.limit}")
	private int limit;
	@Value("${pageable.values.offset}")
	private int offset;
	
	@Value("${pageable.date.limit}")
	private int dateLimit;
	@Value("${pageable.date.offset}")
	private int dateOffset;
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getDateLimit() {
		return dateLimit;
	}
	public void setDateLimit(int dateLimit) {
		this.dateLimit = dateLimit;
	}
	public int getDateOffset() {
		return dateOffset;
	}
	public void setDateOffset(int dateOffset) {
		this.dateOffset = dateOffset;
	}
	
	
	
	
}