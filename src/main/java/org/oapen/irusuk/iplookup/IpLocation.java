package org.oapen.irusuk.iplookup;

/**
 * IpLocation represents an ip address together with its 
 * geographical information
 * 
 * @author acdhirr
 *
 */
public class IpLocation {

	private Long ip;
	private String country;
	private String countryCode;
	private String city;
	private Float latitude;
	private Float longitude;
	
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
	
	public Float getLatitude() {
		return latitude;
	}
	
	public Float getLongitude() {
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
	
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	
	public void setLongitude(Float longitude) {
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

