package org.oapen.irusuk.iplookup;

public class IpLocation {

	private Long ip;
	private String country;
	private String countryCode;
	private String city;
	private Double latitude;
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
	
	public void setIp(Long ip) {
		this.ip = ip;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
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
		IpLocation other = (IpLocation) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		return true;
	}
	
}

