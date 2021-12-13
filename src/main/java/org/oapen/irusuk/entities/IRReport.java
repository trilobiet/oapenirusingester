package org.oapen.irusuk.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * IRUS-UK API JSON representation for Report root node
 * 
 * @author acdhirr
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)

public class IRReport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final ReportHeader header;
	private final List<ReportItem> items;
	
	@JsonCreator
	public IRReport (
		@JsonProperty(value="Report_Header", required = true) ReportHeader header,
		@JsonProperty(value="Report_Items", required = false) List<ReportItem> items
	) {
		this.header = header;
		// Items may not be available when the report header includes
		// an Exceptions node. In that case return an empty Item list.
		this.items = items != null ? items : new ArrayList<>();
	}	
	
	public ReportHeader getHeader() {
		return header;
	}

	public List<ReportItem> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "{" + header + ", 'ReportItems': " + items + "}";
	}
	
}
