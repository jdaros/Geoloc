import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.*;
import static play.test.Helpers.*;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;

import play.libs.Json;
import play.mvc.Result;

/**
 * Class FunctionalTest
 * details : JUNIT Functional Test
 * @author : JDS (TD)
 * @version : 1.0 - Initial version
 */
public class FunctionalTest {

	/**
	 *  Functional Test nominal test case of the findIp function 
	 *  @see Application
	 */
	@Test
    public void findIpTestNominal() {
		
        running(fakeApplication(), new Runnable() {
           @Override
		public void run() {
        	   
        	   Result result = route(fakeRequest(GET,"/json/78.225.149.183"));
       		   assertThat(status(result)).isEqualTo(OK);
               
               // Test of Http.StatusCode (200 here : Http.StatusCode.OK)
               assertThat(status(result)).isEqualTo(OK);
               
               JsonNode resultNode = Json.parse(contentAsString(result));
               assertEquals(resultNode.get("status").getTextValue(),"success");
       		   assertEquals(resultNode.get("country").getTextValue(),"France");
			   assertEquals(resultNode.get("latitude").getTextValue(),"44.8101");
			   assertEquals(resultNode.get("longitude").getTextValue(),"-0.6413");
			   assertEquals(resultNode.get("region").getTextValue(),"Aquitaine");
			   assertEquals(resultNode.get("city").getTextValue(),"Pessac");
           }
        });
    }
	/* End of findIpTestNominal() function */
		
	/**
	 *  Functional Test test case of the findIp function with wrong IP address 
	 *  @see Application
	 */
	@Test
    public void findIpTestWrongIPFormat() {

        running(fakeApplication(), new Runnable() {
           @Override
		public void run() {
        	   
        	   Result result = route(fakeRequest(GET,"/json/78.225.149.1834"));
               
               // Test of Http.StatusCode (Error 400 here : Http.StatusCode.BAD_REQUEST)
               assertThat(status(result)).isEqualTo(BAD_REQUEST);  
               
               JsonNode resultNode = Json.parse(contentAsString(result));
               assertEquals(resultNode.get("status").getTextValue(),"invalid_request");
           }
        });
    }
	/* End of findIpTestWrongIP() function */
	
	
	
}