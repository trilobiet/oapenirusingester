package org.oapen.irusuk.entities;

import java.util.stream.LongStream;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * IRUS-UK API JSON representation for IpAddress (Client_IP)
 * 
 * @author acdhirr
 *
 */
@JsonDeserialize(using = IpAddressDeserializer.class)
public class IpAddress {

	private final String ip;

	public IpAddress(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}
	
	/**
	 * Get a numerical representation for an IPv4 address.
	 * <br/>
	 * Ip address: a.b.c.d
	 * <br/>
	 * a*256^3 + b*256^2 + c*256^1 + d*256^0 
	 * 
	 * @return Numerical representation of this address when in IPv4 format.
	 * 
	 */
	public long toIp4Number() {
		
		if (ip==null) return 0;
		
		String[] groups = ip.split("\\.");
		
		Long q = LongStream.range(0, groups.length)
			.map(b -> {
				Integer cur = valueOfOrZero(groups[(int)b]);
				Long pow = Double.valueOf(Math.pow(256, 3-b)).longValue();
				return cur * pow; 
			}).sum();
				
		return q;
	}
	
	
	private Integer valueOfOrZero(String s) {
		
		try {
			return Integer.valueOf(s);
		} catch ( NumberFormatException e ) {
			return 0;
		}
	}
	

	@Override
	public String toString() {
		return "IpAddress [ip=" + ip + ", getIpNumber()=" + toIp4Number() + "]";
	}
	
	
}
