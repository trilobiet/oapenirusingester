package org.oapen.irusuk.dataingestion.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.oapen.irusuk.dataingestion.Item;

/**
 * Subclass of {@link org.oapen.irusuk.dataingestion.Item} suitable for use with JPA.
 * @author acdhirr
 *
 */
@Entity(name = "Item")
@Table(name = "item")

public class ItemDTO extends Item {

	@Id @Column(nullable=false)
	private String id; // like "20.500.12657/34945"
	private String title, authors, doi, isbn, type, platform,
		publisherName, publisherId, grantNumber, grantProgram, irusId;
	private Short year;
	
    @ManyToMany(
    	fetch = FetchType.EAGER, // Eager, because there are only a few.	
    	cascade = {CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinTable(name = "item_funder",
        joinColumns = @JoinColumn(name = "item_id"),
        inverseJoinColumns = @JoinColumn(name = "funder_id")
    )
	private Set<FunderDTO> funders = new HashSet<>();

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
	
	public Set<FunderDTO> getFunders() {
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

	public void addFunder(FunderDTO funder) {
        funders.add(funder);
        funder.items.add(this);
    }

	@Override
	public String toString() {
		return "ItemDTO [id=" + id + ", title=" + getTitle() + "]";
	}

}
