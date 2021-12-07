package org.oapen.irusuk.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IPAddressTests {
	
	@Test
	void testIpAddressToNumber() {
		
		IpAddress ia1 = new IpAddress("161.132.13.1");
		assertEquals(2709785857L,ia1.toIp4Number());
		
		IpAddress ia2 = new IpAddress("0.0.0.0");
		assertEquals(0L,ia2.toIp4Number());
		
		IpAddress ia3 = new IpAddress("83.163.15.48");
		assertEquals(1403195184L,ia3.toIp4Number());
		
		IpAddress ia4 = new IpAddress("83.163");
		assertEquals(1403191296L,ia4.toIp4Number());
		
		IpAddress ia5 = new IpAddress("100");
		assertEquals(1677721600L,ia5.toIp4Number());

		IpAddress ia6 = new IpAddress("0");
		assertEquals(0L,ia6.toIp4Number());

		// ignore digits too many
		IpAddress ia7 = new IpAddress("83.163.15.48.666.888.999");
		assertEquals(1403195184L,ia7.toIp4Number());
		
		IpAddress ia8 = new IpAddress("");
		assertEquals(0L,ia8.toIp4Number());

		IpAddress ia9 = new IpAddress("83.163.whatever.48");
		assertEquals(1403191344L,ia9.toIp4Number());
		
		IpAddress ia10 = new IpAddress(null);
		assertEquals(0,ia10.toIp4Number());
	}
	
}
