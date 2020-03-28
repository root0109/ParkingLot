/**
 * 
 */
package io.gojek.parkinglot.processor;

import static io.gojek.parkinglot.exception.ParkingKonstants.INVALID_VALUE;

import io.gojek.parkinglot.exception.ParkingLotException;
import io.gojek.parkinglot.model.Car;
import io.gojek.parkinglot.processor.data.InteractiveRequest;
import io.gojek.parkinglot.processor.data.ParkingAction;
import io.gojek.parkinglot.processor.data.Request;
import io.gojek.parkinglot.service.AbstractService;
import io.gojek.parkinglot.service.ParkingService;
import io.gojek.parkinglot.util.InputValidationUtil;

/**
 * @author vaibhav.singh
 *
 */
public class InteractiveProcessor implements AbstractProcessor
{
	private ParkingService parkingService;

	@Override
	public boolean validateInput(Request request)
	{
		boolean valid = false;
		if (request instanceof InteractiveRequest)
		{
			InteractiveRequest interactiveRequest = (InteractiveRequest) request;
			valid = InputValidationUtil.validate(interactiveRequest.getInputString());
		}
		return valid;
	}

	@Override
	public boolean execute(Request request) throws ParkingLotException
	{
		InteractiveRequest interactiveRequest = (InteractiveRequest) request;
		int level = 1;
		String[] inputs = interactiveRequest.getInputString().split(" ");
		ParkingAction action = ParkingAction.getAction(inputs[0]);
		switch (action)
		{
			case CREATE_PARKING_LOT :
				try
				{
					int capacity = Integer.parseInt(inputs[1]);
					parkingService.createParkingLot(level, capacity);
				}
				catch (NumberFormatException e)
				{
					throw new ParkingLotException(INVALID_VALUE);
				}
				break;
			case PARK :
				parkingService.park(level, new Car(inputs[1], inputs[2]));
				break;
			case LEAVE :
				try
				{
					int slotNumber = Integer.parseInt(inputs[1]);
					parkingService.unPark(level, slotNumber);
				}
				catch (NumberFormatException e)
				{
					throw new ParkingLotException(INVALID_VALUE);
				}
				break;
			case STATUS :
				parkingService.getStatus(level);
				break;
			case REG_NUMBER_FOR_CARS_WITH_COLOR :
				parkingService.getRegNumberForColor(level, inputs[1]);
				break;
			case SLOTS_NUMBER_FOR_CARS_WITH_COLOR :
				parkingService.getSlotNumbersFromColor(level, inputs[1]);
				break;
			case SLOTS_NUMBER_FOR_REG_NUMBER :
				parkingService.getSlotNoFromRegistrationNo(level, inputs[1]);
				break;
			default:
				break;
		}
		return true;
	}

	@Override
	public void setService(AbstractService service)
	{
		parkingService = (ParkingService) service;
	}
}
