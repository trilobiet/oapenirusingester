package org.oapen.irusuk.entities;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class IpAddressDeserializer extends JsonDeserializer<IpAddress> {

	@Override
	public IpAddress deserialize(JsonParser p, DeserializationContext ctxt) 
		throws IOException, JsonProcessingException {
		
		String value = p.getValueAsString();
		return new IpAddress(value);
    }
	
}
