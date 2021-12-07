package org.oapen.irusuk.dataingestion.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// Read Only, no setters
@Entity
@Table(name = "iplocation")

public class IpLocationDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IP_FROM")
	public Long ipFrom;
	@Column(name="IP_TO")
	public Long ipTo;
	@Column(name="COUNTRY_CODE")
	public String countryCode;
	@Column(name="COUNTRY_NAME")
	public String country;
	@Column(name="REGION")
	public String region;
	@Column(name="CITY")
	public String city;
	@Column(name="LATITUDE")
	public Double latitude;
	@Column(name="LONGITUDE")
	public Double longitude;
	
	@Override
	public String toString() {
		return "IpLocation [ipFrom=" + ipFrom + ", ipTo=" + ipTo 
				+ ", countryCode=" + countryCode + ", country="
				+ country + ", region=" + region + ", city=" + city 
				+ ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}

}
