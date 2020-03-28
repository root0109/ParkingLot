/**
 * 
 */
package io.gojek.parkinglot.exception;

/**
 * @author vaibhav.singh
 *
 */
public class ParkingLotException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2203617332613824129L;

	public ParkingLotException()
	{
		super();
	}

	public ParkingLotException(String message)
	{
		super(message);
	}

	public ParkingLotException(Throwable cause)
	{
		super(cause);
	}

	public ParkingLotException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
