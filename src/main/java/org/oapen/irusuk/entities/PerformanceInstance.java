package org.oapen.irusuk.entities;

import java.io.Serializable;
import java.time.YearMonth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * IRUS-UK API JSON representation for 'Performance_Instances' list entry
 * @author acdhirr
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)

public class PerformanceInstance implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final YearMonth eventMonth;	
	private final IpAddress clientIP;
	private final Country country;	
	private final MetricTypeCounts metricTypeCounts;
	
	@JsonCreator
	public PerformanceInstance(
		@JsonProperty(value="Event_Month", required = true) YearMonth eventMonth,
		@JsonProperty(value="Client_IP", required = true) IpAddress clientIP,
		@JsonProperty(value="Country", required = true) Country country,
		@JsonProperty(value="Metric_Type_Counts", required = true) MetricTypeCounts metricTypeCounts
	) {
		this.eventMonth = eventMonth; 
		this.clientIP = clientIP;
		this.country = country;
		this.metricTypeCounts = metricTypeCounts;
	}	
	
	
	public YearMonth getEventMonth() {
		return eventMonth;
	}

	public IpAddress getClientIP() {
		return clientIP;
	}

	public Country getCountry() {
		return country;
	}

	public MetricTypeCounts getMetricTypeCounts() {
		return metricTypeCounts;
	}

	@Override
	public String toString() {
		return "{'eventMonth': '" + eventMonth 
				+ "', 'clientIP': '" + clientIP + "', " + country
				+ ", " + metricTypeCounts + "}";
	}	
	
}
