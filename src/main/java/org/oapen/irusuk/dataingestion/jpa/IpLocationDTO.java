package org.oapen.irusuk.dataingestion.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.oapen.irusuk.iplookup.IpLocation;

// 
/**
 * Subclass of {@link org.oapen.irusuk.iplookup.IpLocation} suitable for use with JPA.
 * (Read only)
 * 
 * @author acdhirr
 *
 */
@Entity
@Table(name = "iplocation")

public class IpLocationDTO extends IpLocation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IP_FROM")
	private Long ip;
	@Column(name="COUNTRY_NAME")
	private String country;
	@Column(name="COUNTRY_CODE")
	private String countryCode;
	@Column(name="CITY")
	private String city;
	@Column(name="LATITUDE")
	private Double latitude;
	@Column(name="LONGITUDE")
	private Double longitude;
	
	public Long getIp() {
		return ip;
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
	
	@Override
	public String toString() {
		return "IpLocationDTO [ip=" + ip + ", countryCode=" + countryCode 
			+ ", city=" + city + ", latitude=" + latitude
			+ ", longitude=" + longitude + "]";
	}
	
}
