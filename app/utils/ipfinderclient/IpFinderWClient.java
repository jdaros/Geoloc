package utils.ipfinderclient;

import models.IpInformation;
import org.codehaus.jackson.JsonNode;

import play.libs.F.Promise;
import play.libs.WS;
import utils.InvalidAttributeException;
import static play.mvc.Http.Status.*;

/**
 * Class IpFinderWClient
 * details : Client implementation to ask ip-api.com API about geolocation coordinates of a device thx to its IP
 * @author : JDS (TD)
 * @version : 1.0 - Initial version
 */
public class IpFinderWClient {
	
	// address of the web service
	private static final String WSADRESS = "http://ip-api.com/json/";
	
	/**
	 * Function to get location information relative to an IP
	 * @param  par_Ip the IP to look for.
	 * @return IpInformation object, geolocation information of par_IP
	 * @throws InvalidAttributeException if IP is invalid
	 * @throws ErrorStatusCodeException if WS IP-API send an error status
	 * @see <a href="http://ip-api.com/docs/"> IP-API documentation</a>
	 */
	public static IpInformation get(String par_Ip) throws InvalidAttributeException, ErrorStatusCodeException{
		
		/** @see IpInformation  */
		IpInformation info = null;
		
		// HTTP Request from a Play function
		/** TODO : Add action to control WS used below (timeout, connectException ...) **/
		Promise<WS.Response> resp = WS.url(WSADRESS + par_Ip).get();

		// Test of the status Code of the IP-API webservice
		int statusCode = resp.get().getStatus();
		
		switch (statusCode) {
		
			// If HTTP status code is 200
			case OK : 
				
				// Because IP-API is sending a JSON result, we transform the answer as a JSON object
				JsonNode jsonCvr = resp.get().asJson();
				
				// the following status is returned in JSON response
				String statusJSON = jsonCvr.get("status").asText();
				
				// even if status code is 200, the api send a status in JSON. If success, the IP is considered as valid
				if(statusJSON != null && statusJSON.equals("success")){

					String lat = jsonCvr.get("lat").asText();
					String lon= jsonCvr.get("lon").asText();
					String city = jsonCvr.get("city").asText();
					String region = jsonCvr.get("regionName").asText();
					String country = jsonCvr.get("country").asText();
					
					// implementation of a object to store our IP information.
					info = new IpInformation(lat, lon, city, region, country);
				}
				else { // the IP is not valid
					throw new InvalidAttributeException("IP: " + par_Ip + " is not valid" );	
				}
			break;

			// if the status code returned by IP-API is not 200, an exception is thrown
			default: throw new ErrorStatusCodeException("HTTP Status code : " +  statusCode);	
		} 
		return info;
	}
	/* End of get() function */
}