/**
 * 
 */
package io.gojek.parkinglot.exception;

/**
 * @author vaibhav.singh
 *
 */
public final class ParkingKonstants
{
	private ParkingKonstants()
	{
	}

	public static final String PARKING_ALREADY_EXIST   = "Sorry Parking Already Created, It CAN NOT be again recreated.";
	public static final String PARKING_NOT_EXIST_ERROR = "Sorry, Car Parking Does not Exist";
	public static final String INVALID_VALUE           = "Invalid Value/s";
	public static final String INVALID_FILE            = "Invalid File";
	public static final String PROCESSING_ERROR        = "Processing Error. System Exiting ... ";
	public static final String INVALID_REQUEST         = "Invalid Param, Please enter Command carefully...";

	public static final int NOT_AVAILABLE          = -1;
	public static final int VEHICLE_ALREADY_PARKED = -2;
	public static final int NOT_FOUND              = -3;
}
