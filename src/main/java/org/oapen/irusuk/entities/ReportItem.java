package org.oapen.irusuk.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)

public class ReportItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final String item; 	
	private final String uri;

	@JsonProperty(value="Publisher")
	private String publisher;
	@JsonProperty(value="Publisher_ID")
	private String publisherId;
	@JsonProperty(value="Authors")
	private String authors;
	@JsonProperty(value="DOI")
	private String doi;
	@JsonProperty(value="ISBN")
	private String isbn;
	@JsonProperty(value="Item_Type")
	private String itemType;
	@JsonProperty(value="YOP")
	private Short yearOfPublishing;
	@JsonProperty(value="OAPEN_Grant_Number")
	private String grantNumber;
	@JsonProperty(value="OAPEN_Grant_Program")
	private String grantProgram;
	@JsonProperty(value="Platform")
	private String platform;
	@JsonProperty(value="IRUS_Item_ID")
	private String itemId;
	@JsonProperty(value="Funders")
	private List<Funder> funders = new ArrayList<>();
	@JsonProperty(value="Performance_Instances")
	private List<PerformanceInstance> performanceInstances = new ArrayList<>();
	
	@JsonCreator
	public ReportItem(
		@JsonProperty(value="Item", required = true) String item,
		@JsonProperty(value="URI", required = true) String uri
	) {
		this.item = item;
		this.uri = uri;
	}	
	
	public String getItem() {
		return item;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public String getAuthors() {
		return authors;
	}

	public String getDoi() {
		return doi;
	}

	public String getISBN() {
		return isbn;
	}

	public String getUri() {
		return uri;
	}

	public String getItemType() {
		return itemType;
	}

	public Short getYearOfPublishing() {
		return yearOfPublishing;
	}

	public String getGrantNumber() {
		return grantNumber;
	}

	public String getGrantProgram() {
		return grantProgram;
	}

	public String getPlatform() {
		return platform;
	}

	public String getItemId() {
		return itemId;
	}

	public List<Funder> getFunders() {
		return funders;
	}

	public List<PerformanceInstance> getPerformanceInstances() {
		return performanceInstances;
	}

	
	@Override
	public String toString() {
		return "{'item': '" + item + "', 'publisher': '" + publisher 
				+ "', 'publisherId': '" + publisherId + "', 'authors': '"
				+ authors + "', 'uri': '" + uri + "', 'itemType': '" 
				+ itemType + "', 'yearOfPublishing':" + yearOfPublishing
				+ ", 'grantNumber': '" + grantNumber 
				+ "', 'grantProgram': '" + grantProgram
				+ "', 'platform':" + platform + "', 'itemId':" + itemId
				+ "', 'funders': " + funders 
				+ ", 'performanceInstances': " + performanceInstances + "}";
	}
}
