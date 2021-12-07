package org.oapen.irusuk;

import java.time.YearMonth;

public interface AppStatus {
	
	YearMonth getLastIngestedMonth();
	void setLastIngestedMonth(YearMonth ym);

}
