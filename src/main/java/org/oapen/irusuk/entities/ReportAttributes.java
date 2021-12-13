package org.oapen.irusuk.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * IRUS-UK API JSON representation for 'Report_Attributes' node
 * @author acdhirr
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)

public class ReportAttributes implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final String attributesToShow;
	
	@JsonCreator
	public ReportAttributes(
		@JsonProperty(value="Attributes_To_Show", required = true) String attributesToShow
	) {
		this.attributesToShow = attributesToShow; 
	}	
	
	public String getAttributesToShow() {
		return attributesToShow;
	}

	@Override
	public String toString() {
		return "'ReportAttributes': {'attributesToShow': '" 
			+ attributesToShow + "'}";
	}
}
