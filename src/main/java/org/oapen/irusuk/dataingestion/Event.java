package org.oapen.irusuk.dataingestion;

import java.time.LocalDate;


public class Event {
	
	private String ip;
	private LocalDate date;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
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
		Event other = (Event) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		
		return "Event [itemId=" + itemId 
				+ ", date=" + date + ", ip=" + ip + "]";
	}


    
}
