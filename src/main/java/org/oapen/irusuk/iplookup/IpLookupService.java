package org.oapen.irusuk.iplookup;

import org.oapen.irusuk.entities.IpAddress;

/**
 * Service to lookup Items
 * @author acdhirr
 *
 * @param <T> The type of IpLocation objects for this service
 */
public interface IpLookupService<T extends IpLocation> {
	
	/**
	 * @param ip Ip address
	 * @return IpLocation for IpAddress ip
	 */
	T findByIp(IpAddress ip); 
}
