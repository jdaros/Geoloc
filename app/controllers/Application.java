package controllers;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;

import models.IpInformation;
import play.libs.Json;
import play.mvc.*;

import utils.InvalidAttributeException;
import utils.ipfinderclient.ErrorStatusCodeException;
import utils.ipfinderclient.IpFinderWClient;

/**
 * Class Application - Play! Framework controller
 * details : Here are defined the controllers associated to the Application
 * @author : JDS (TD)
 * @version : 1.0 - Initial version
 */
public class Application extends Controller {
  
	/**
	 *  Controller to return a JSON render of the position of a device thanks to its IP
	 *  @param par_ip defining the IP of the device to geolocate
	 *  @return render status 
	 */
    public static Result findIp(String par_ip) {
    	
    	JsonNode resultJSON = null;
    	Map<String, Object> resultMap = null;
    	
    	try {
    		
    		/** @see IpInformation   */
    		/** @see IpFinderWClient */
    		IpInformation ipInfo = IpFinderWClient.get(par_ip);	
    		
    		resultMap = new HashMap<String, Object>();
    		resultMap.put("status", "success");
    		resultMap.put("latitude", ipInfo.latitude);
    		resultMap.put("longitude", ipInfo.longitude);
    		resultMap.put("city", ipInfo.city);
    		resultMap.put("region", ipInfo.region);
    		resultMap.put("country", ipInfo.country);
    		
    		resultJSON = Json.toJson(resultMap);
    		
    		return ok(resultJSON); 	
    	}
    	catch (InvalidAttributeException e){
    		
    		return badIp(par_ip, "json");
    		
    	} catch (ErrorStatusCodeException e) {
    		
    		resultMap = new HashMap<String, Object>();
    		resultMap.put("status", "internal_server_error");
    		resultJSON = Json.toJson(resultMap);
    		
    		return internalServerError(resultJSON);
		}
    }
    /* End of findIp() function */
    
    
    /**
	 *  Controller to return an HTML render of the position of a device thanks to its IP
	 *  @param par_ip defining the IP of the device to geolocate
	 *  @return render status 
	 */
    
    public static Result index(String par_ip) {
   
    	try {
    		
    		/** @see IpInformation   */
    		/** @see IpFinderWClient */
    		
    		IpInformation ipInfo = IpFinderWClient.get(par_ip);
    		return ok(views.html.showIp.render(ipInfo,par_ip));
    	}
    	catch (InvalidAttributeException e){
    		
    		return badIp(par_ip, "html");
    		
    	} catch (ErrorStatusCodeException e) {
		
    		return internalServerError("Internal Server Error");
		}		
    }
    /* End of index() function */
    
    
    /**
	 *  Controller to return an HTML or JSON render in case of bad IP format
	 *  @param par_ip     IP of the device to geolocate
	 *  @param par_format format of the render 
	 *  @return render status 
	 */
    
    public static Result badIp(String par_ip, String par_format){
   
    	if(par_format.equals("json")){
    		
    		HashMap<String, Object> resultMap = new HashMap<String, Object>();
    		resultMap.put("status", "invalid_request");
    		JsonNode resultJSON = Json.toJson(resultMap);

    		return badRequest(resultJSON);	
    	}
    	else {
    		return badRequest("Invalid IP Attribute");
    	}
	}
    /* End of badIp() function */
}