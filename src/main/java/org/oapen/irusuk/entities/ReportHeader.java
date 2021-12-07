package org.oapen.irusuk.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)

public class ReportHeader implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Report_Name")
	private String reportName;
	@JsonProperty("Report_ID")
	private String reportID;
	@JsonProperty("Release")
	private String release;
	@JsonProperty("Institution_Name")
	private String institution;
	@JsonProperty("Institution_ID")
	private String institutionID;
	@JsonProperty("Report_Filters")
	private ReportFilters reportFilters;
	@JsonProperty("Report_Attributes")
	private ReportAttributes reportAttributes;
	@JsonProperty("Exceptions")
	private List<HeaderException> exceptions = new ArrayList<>();;
	@JsonProperty("Created")
	private LocalDate created;
	@JsonProperty("Created_By")
	private String createdBy;
	@JsonProperty("API_Version")
	private String apiVersion;
	
	public String getReportName() {
		return reportName;
	}

	public String getReportID() {
		return reportID;
	}

	public String getRelease() {
		return release;
	}

	public String getInstitution() {
		return institution;
	}

	public String getInstitutionID() {
		return institutionID;
	}

	public ReportFilters getReportFilters() {
		return reportFilters;
	}

	public ReportAttributes getReportAttributes() {
		return reportAttributes;
	}

	public List<HeaderException> getExceptions() {
		return exceptions;
	}

	public LocalDate getCreated() {
		return created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	@Override
	public String toString() {
		return "'ReportHeader': {'reportName':'" + reportName + "', 'reportID':'" + reportID + "', 'release': '" + release
				+ "', 'institution': '" + institution + "', 'institutionID': '" + institutionID + "', "
				+ reportFilters + ", " + reportAttributes 
				+ ", 'exceptions': " + exceptions
				+ ", 'created': '" + created + "', 'createdBy': '" + createdBy + "', 'apiVersion': '" + apiVersion + "'}";
	}
}
