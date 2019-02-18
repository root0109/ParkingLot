/**
 * 
 */
package io.gojek.parkinglot.exception;

/**
 * @author vaibhav
 *
 */
public class ParkingException extends Exception
{
	private static final long serialVersionUID = -3552275262672621625L;
	
	private String		errorCode		= null;	// this will hold system defined error code
	private Object[]	errorParameters	= null;	// this will hold parameters for error code/message
	
	/**
	 * @param message
	 * @param throwable
	 */
	public ParkingException(String message, Throwable throwable)
	{
		super(message, throwable);
	}
	
	/**
	 * @param message
	 */
	public ParkingException(String message)
	{
		super(message);
	}
	
	/**
	 * @param throwable
	 */
	public ParkingException(Throwable throwable)
	{
		super(throwable);
	}
	
	/**
	 * @param errorCode
	 * @param message
	 * @param errorParameters
	 */
	public ParkingException(String errorCode, String message, Object[] errorParameters)
	{
		super(message);
		this.setErrorCode(errorCode);
		this.setErrorParameters(errorParameters);
	}
	
	/**
	 * @param errorCode
	 * @param message
	 * @param throwable
	 */
	public ParkingException(String errorCode, String message, Throwable throwable)
	{
		super(message, throwable);
		this.setErrorCode(errorCode);
	}
	
	/**
	 * @param errorCode
	 * @param message
	 * @param errorParameters
	 * @param throwable
	 */
	public ParkingException(String errorCode, String message, Object[] errorParameters, Throwable throwable)
	{
		super(message, throwable);
		this.setErrorCode(errorCode);
		this.setErrorParameters(errorParameters);
	}
	
	public String getErrorCode()
	{
		return errorCode;
	}
	
	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}
	
	public Object[] getErrorParameters()
	{
		return errorParameters;
	}
	
	public void setErrorParameters(Object[] errorParameters)
	{
		this.errorParameters = errorParameters;
	}
}
