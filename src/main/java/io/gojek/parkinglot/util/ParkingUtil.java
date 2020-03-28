package io.gojek.parkinglot.util;

import io.gojek.parkinglot.exception.ParkingLotException;
import io.gojek.parkinglot.processor.data.Action;
import io.gojek.parkinglot.processor.data.InteractiveRequest;
import io.gojek.parkinglot.processor.data.ParkingAction;

/**
 * 
 * @author vaibhav.singh
 *
 */
public final class ParkingUtil
{
	private ParkingUtil()
	{
	}

	public static InteractiveRequest getInteractiveRequest(String input)
	{
		return new InteractiveRequest(input);
	}

	public static Action getParkingAction(String input) throws ParkingLotException
	{
		return ParkingAction.getAction(input);
	}
}
