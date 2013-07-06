package utils.ipfinderclient;

/**
 * Class ErrorStatusCodeException
 * details : Exception for a status code that is an ERROR
 * @author : JDS (TD)
 * @version : 1.0 - Initial version
 */
public class ErrorStatusCodeException extends Exception {

	private static final long serialVersionUID = -608612866403073230L;
	
	/** 
	* ErrorStatusCodeException new instance
	*/  
	public ErrorStatusCodeException() {}  
	
	/** 
	* ErrorStatusCodeException new instance
	* @param message exception details message 
	*/  
	public ErrorStatusCodeException(String message) {  
		super(message); 
	}  
	/** 
	* ErrorStatusCodeException new instance
	* @param cause origin exception
	*/  
	public ErrorStatusCodeException(Throwable cause) {  
		super(cause); 
	}  
	/** 
	* ErrorStatusCodeException new instance
	* @param message exception details message 
	* @param cause origin exception
	*/  
	public ErrorStatusCodeException(String message, Throwable cause) {  
		super(message, cause); 
	} 
}