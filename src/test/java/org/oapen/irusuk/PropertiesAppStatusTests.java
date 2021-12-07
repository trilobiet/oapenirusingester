package org.oapen.irusuk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropertiesAppStatusTests {
	
	private final String appStatusPath = 
		"src/test/resources/appstatus/.irusuk/app-state.properties";

	private final AppStatus statusService = 
		new PropertiesAppStatusService(appStatusPath); 
	
	@Test
	void testSetAndGetlastIngestedMonth() {
		
		YearMonth ym = YearMonth.now();
		statusService.setLastIngestedMonth(ym);
		YearMonth lim = statusService.getLastIngestedMonth();
		assertEquals(lim,ym);
	}
	

}
