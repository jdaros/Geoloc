package utils;

/**
 * Class InvalidAttributeException
 * details : Exception for a malformed attribute
 * @author : JDS (TD)
 * @version : 1.0 - Initial version
 */
public class InvalidAttributeException extends Exception {


	private static final long serialVersionUID = 8505640816664213608L;
	
	/** 
	* InvalidAttributeException new instance
	*/  
	public InvalidAttributeException() {}  
	
	/** 
	* InvalidAttributeException new instance
	* @param message exception details message 
	*/  
	public InvalidAttributeException(String message) {  
		super(message); 
	}  
	/** 
	* InvalidAttributeException new instance
	* @param cause origin exception
	*/  
	public InvalidAttributeException(Throwable cause) {  
		super(cause); 
	}  
	/** 
	* InvalidAttributeException new instance
	* @param message exception details message 
	* @param cause origin exception
	*/  
	public InvalidAttributeException(String message, Throwable cause) {  
		super(message, cause); 
	} 
}

