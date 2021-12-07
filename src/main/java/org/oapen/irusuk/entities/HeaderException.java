package org.oapen.irusuk.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)

public class HeaderException implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final String code;
	private final String severity;
	private final String message;
	private final String data;
	
	@JsonCreator
	public HeaderException(
		@JsonProperty(value="Code") String code,
		@JsonProperty(value="Severity") String severity,
		@JsonProperty(value="Message", required = true) String message,
		@JsonProperty(value="Data") String data
	) {
		this.code = code; 
		this.severity = severity;
		this.message = message;
		this.data = data;
	}	
	
	public String getCode() {
		return code;
	}

	public String getSeverity() {
		return severity;
	}

	public String getMessage() {
		return message;
	}

	public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return "{'code': '" + code + "', 'severity': '" + severity 
			+ "', 'message': '" + message + "', 'data': '" + data + "'}";
	}
	
}
