package org.oapen.irusuk.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)

public class MetricTypeCounts implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Integer totalItemInvestigations;
	private final Integer totalItemRequests;
	private final Integer uniqueItemInvestigations;
	private final Integer uniqueItemRequests;
	
	@JsonCreator
	public MetricTypeCounts(
		@JsonProperty(value="Total_Item_Investigations") Integer totalItemInvestigations,
		@JsonProperty(value="Total_Item_Requests", required = true) Integer totalItemRequests,
		@JsonProperty(value="Unique_Item_Investigations") Integer uniqueItemInvestigations,
		@JsonProperty(value="Unique_Item_Requests") Integer uniqueItemRequests
	) {
		this.totalItemInvestigations = totalItemInvestigations;
		this.totalItemRequests = totalItemRequests;
		this.uniqueItemInvestigations = uniqueItemInvestigations;
		this.uniqueItemRequests = uniqueItemRequests;
	}
	
	public Integer getTotalItemInvestigations() {
		return totalItemInvestigations;
	}

	public Integer getTotalItemRequests() {
		return totalItemRequests;
	}

	public Integer getUniqueItemInvestigations() {
		return uniqueItemInvestigations;
	}

	public Integer getUniqueItemRequests() {
		return uniqueItemRequests;
	}

	@Override
	public String toString() {
		return "'MetricTypeCounts': {'totalItemInvestigations':" + totalItemInvestigations + ", 'totalItemRequests':"
				+ totalItemRequests + ", 'uniqueItemInvestigations':" + uniqueItemInvestigations + ", 'uniqueItemRequests':"
				+ uniqueItemRequests + "}";
	}
	
}
