package org.oapen.irusuk.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * IRUS-UK API JSON representation for 'Country' node
 * 
 * @author acdhirr
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)

public class Country implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final String code;
	private final String name;
	
	@JsonCreator
	public Country(
		@JsonProperty(value="Country_Code", required = true) String code,
		@JsonProperty(value="Country", required = true) String name
	) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "'Country': {'code': '" + code + "', 'name': '" + name + "'}";
	}
	
}
