package org.oapen.irusuk.dataingestion;

import java.util.HashSet;
import java.util.Set;

public class Item {

	private String id; 
	private String title, authors, doi, isbn, type, platform;
	private String publisherName, publisherId, grantNumber, grantProgram, irusId;
	private Short year;
	private Set<? extends Funder> funders = new HashSet<>();
	
    public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthors() {
		return authors;
	}

	public String getDoi() {
		return doi;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getType() {
		return type;
	}

	public String getPlatform() {
		return platform;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public String getGrantNumber() {
		return grantNumber;
	}

	public String getGrantProgram() {
		return grantProgram;
	}

	public String getIrusId() {
		return irusId;
	}

	public Short getYear() {
		return year;
	}

	public Set<? extends Funder> getFunders() {
		return funders;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public void setGrantNumber(String grantNumber) {
		this.grantNumber = grantNumber;
	}

	public void setGrantProgram(String grantProgram) {
		this.grantProgram = grantProgram;
	}

	public void setIrusId(String irusId) {
		this.irusId = irusId;
	}

	public void setYear(Short year) {
		this.year = year;
	}

	public void setFunders(Set<? extends Funder> funders) {
		this.funders = funders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", funders=" + funders + "]";
	}
		
}
