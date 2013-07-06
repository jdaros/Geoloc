package utils.ipfinderclient;

import static org.junit.Assert.*;

import models.IpInformation;
import utils.InvalidAttributeException;
import org.junit.Test;

/**
 * Class IpFinderWClientTest
 * details : JUNIT Test of IpFinderWClient class
 * @author : JDS (TD)
 * @version : 1.0 - Initial version
 * @see IpFinderWClient
 */
public class IpFinderWClientTest {

	/**
	 * Nominal test case of the get function
	 * @throws ErrorStatusCodeException 
	 * @throws utils.InvalidAttributeException 
	 */	
	@Test
	public void getTestNominal() throws utils.InvalidAttributeException, ErrorStatusCodeException {
		
		IpInformation ipInfo = IpFinderWClient.get("78.225.149.183");
		assertEquals(ipInfo.city,"Pessac");
		assertEquals(ipInfo.country,"France");
		assertEquals(ipInfo.latitude,"44.8101");
		assertEquals(ipInfo.longitude,"-0.6413");
		assertEquals(ipInfo.region,"Aquitaine");
	}
	
	/**
	 * Test case of the get function with Invalid IP
	 * @throws ErrorStatusCodeException 
	 * @throws utils.InvalidAttributeException 
	 */	
	@Test(expected = InvalidAttributeException.class)
	public void getTestRobustness() throws utils.InvalidAttributeException, ErrorStatusCodeException{
		
		@SuppressWarnings("unused")
		IpInformation ipInfo = IpFinderWClient.get("78.225.149.1834");
		
	}
}
