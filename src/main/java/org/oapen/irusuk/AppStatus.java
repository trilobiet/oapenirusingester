package org.oapen.irusuk;

import java.time.YearMonth;

/**
 * Interface to keep track of last successfully 
 * ingested month.
 * 
 * @author acdhirr
 *
 */
public interface AppStatus {
	
	/**
	 * @return year/month of last successfully ingested month. 
	 */
	YearMonth getLastIngestedMonth();
	
	/**
	 * Set last successfully ingested year/month number. 
	 * @param ym
	 */
	void setLastIngestedMonth(YearMonth ym);
}
