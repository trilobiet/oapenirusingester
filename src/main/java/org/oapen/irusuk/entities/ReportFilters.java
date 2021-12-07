package org.oapen.irusuk.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)

public class ReportFilters implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final LocalDate beginDate;
	private final LocalDate endDate;
	private final String platform;
	
	// More fields may be in this filter but they are not used for this application
	
	@JsonCreator
	public ReportFilters(
		@JsonProperty(value="Begin_Date", required = true) LocalDate beginDate,
		@JsonProperty(value="End_Date", required = true) LocalDate endDate,
		@JsonProperty(value="Platform", required = true) String platform
	) {
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.platform = platform;
	}	
	
	public LocalDate getBeginDate() {
		return beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public String getPlatform() {
		return platform;
	}

	@Override
	public String toString() {
		return "'ReportFilters': {'beginDate': '" + beginDate 
				+ "', 'endDate': '" + endDate + "', 'platform': '" + platform
				+ "'}";
	}
	
}
