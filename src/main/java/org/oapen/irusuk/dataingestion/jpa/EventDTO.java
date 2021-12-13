package org.oapen.irusuk.dataingestion.jpa;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.oapen.irusuk.dataingestion.Event;

/** 
 * Subclass of {@link org.oapen.irusuk.dataingestion.Event} suitable for use with JPA. 
 * 
 * @author acdhirr
 *
 */
@Entity(name = "Event")
@Table(name = "event")
@IdClass(EventId.class)

public class EventDTO extends Event  {
	
	/* Combined key of three fields
	 * See https://www.baeldung.com/jpa-composite-primary-keys */
	@Id @Column(nullable=false)
	private String ip;
	@Id @Column(nullable=false)
	private LocalDate date;
	@Id @Column(nullable=false)
	private String itemId;
	
	private String country, countryCode;
	private String city;
	private Double latitude, longitude;
	private Integer requests;
	
	public String getIp() {
		return ip;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getItemId() {
		return itemId;
	}

	public String getCountry() {
		return country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getCity() {
		return city;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public Integer getRequests() {
		return requests;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setRequests(Integer requests) {
		this.requests = requests;
	}

	@Override
	public String toString() {
		return "EventDTO [ip=" + ip + ", date=" + date + ", itemId=" + itemId + "]";
	}
}
