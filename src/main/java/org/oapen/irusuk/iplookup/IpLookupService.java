package org.oapen.irusuk.iplookup;

import org.oapen.irusuk.entities.IpAddress;

public interface IpLookupService<T extends IpLocation> {

	T findByIp(IpAddress ip); 
}
