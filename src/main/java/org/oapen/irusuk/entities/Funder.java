package org.oapen.irusuk.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)

public class Funder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final String name;
	private final String id;
	
	@JsonCreator
	public Funder (
		@JsonProperty(value="Funder_Name", required = true) String name,
		@JsonProperty(value="OAPEN_Funder_UUID", required = true) String id
	) {
		this.name = name; 
		this.id = id;
	}	
	
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "'Funder': {'name':" + name + ", 'id':" + id + "}";
	}
}
