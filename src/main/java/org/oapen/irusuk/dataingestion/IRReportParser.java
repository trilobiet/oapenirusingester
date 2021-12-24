package org.oapen.irusuk.dataingestion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.function.Consumer;

import org.oapen.irusuk.entities.ReportHeader;
import org.oapen.irusuk.entities.ReportItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Parses a report file. 
 * @author acdhirr
 *
 */
public class IRReportParser {
	
	private final File file;
	private final ObjectMapper mapper;
	// Keep track of last successfully parsed item
	private String lastSuccessfullyParsedItemId = "[none]";
	
	private static final Logger logger = 
		LoggerFactory.getLogger(IRReportParser.class);

	/**
	 * @param file File to construct a parser for 
	 */
	public IRReportParser(File file){

		this.file = file;
		this.mapper = getConfiguredObjectMapper();
	}

	/**
	 * Parse the header and send it to a Consumer.
	 * 
	 * @param headerConsumer Consumer function telling what to do with a {@link org.oapen.irusuk.entities.ReportHeader}
	 * @return true when the parsing succeeded without error, false otherwise. 
	 */
	public boolean parseHeader(Consumer<ReportHeader> headerConsumer) {

		try (JsonParser parser = mapper.getFactory().createParser(getInputStream(file).orElseThrow())) {

			while (parser.nextToken() != JsonToken.END_OBJECT) {

				String fieldName = parser.getCurrentName();

				if ("Report_Header".equals(fieldName)) {
					if (parser.nextToken() == JsonToken.START_OBJECT) {
						ReportHeader header = mapper.readValue(parser, ReportHeader.class);
						headerConsumer.accept(header);
					}
				}

				if ("Report_Items".equals(fieldName))
					parser.skipChildren();
			}
		} 
		catch (IOException e) {
			logger.warn("parseHeader threw: " + e);
			return false;
		}

		return true;
	}
	
	/**
	 * Parse the Items and send them each to a Consumer.
	 * @param itemConsumer Consumer function telling what to do with a {@link org.oapen.irusuk.entities.ReportItem}
	 * @return true when the parsing succeeded without error, false otherwise.
	 */
	public boolean parseItems(Consumer<ReportItem> itemConsumer) {

		// Use Jackson JsonParser, it allows streaming
		try (JsonParser parser = mapper.getFactory().createParser(getInputStream(file).orElseThrow())) {

			// Stream the items.
			// Loop until token equals "}"
			while (parser.nextToken() != JsonToken.END_OBJECT) {

				String fieldName = parser.getCurrentName();

				if ("Report_Header".equals(fieldName))
					parser.skipChildren();

				if ("Report_Items".equals(fieldName)) {

					// Report_Items is an array
					if (parser.nextToken() == JsonToken.START_ARRAY) {

						// loop over items until token equals "]"
						while (parser.nextToken() != JsonToken.END_ARRAY) {
							
							Optional<ReportItem> oReportItem = parseItem(parser);
							
							if (oReportItem.isPresent()) {
								
								ReportItem reportItem = oReportItem.get();
								lastSuccessfullyParsedItemId = reportItem.getItemId();
								
								try {
									itemConsumer.accept(reportItem);
								} catch (Exception e) {
									logger.error("Error occured while consuming Item {}: {}", reportItem.getUri(), e);
								}
							}	
						}
					}
				}
			}
		} 
		catch (IOException e) {
			logger.warn("parseItems threw: {} ", e.getMessage());
			return false;
		}

		return true;
	}
	
	
	private Optional<ReportItem> parseItem(JsonParser parser) throws IOException {
		
		Optional<ReportItem> oReportItem = Optional.empty();
		
		try {
			oReportItem = Optional.of(mapper.readValue(parser, ReportItem.class));
		} catch (JsonProcessingException e) {
			logger.error("Error occured while parsing an Item: {}", e.getMessage());
			logger.warn("Last succesfully parsed Item is {}", lastSuccessfullyParsedItemId);
		}
		return oReportItem;
	}
	
	
	private ObjectMapper getConfiguredObjectMapper() {

		ObjectMapper mapper = new ObjectMapper();
		// This handles java YearMonth etc.:
        mapper.registerModule(new JavaTimeModule()); 
        // https://www.javadoc.io/doc/com.fasterxml.jackson.core/jackson-databind/2.13.0/com/fasterxml/jackson/databind/DeserializationFeature.html
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
	}
	

	private Optional<InputStream> getInputStream(File file) {
		
		try {
			return Optional.of(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			logger.warn("getInputStream " + file.getPath() + " threw: " + e);
			return Optional.empty();
		}
	}	

	
}
