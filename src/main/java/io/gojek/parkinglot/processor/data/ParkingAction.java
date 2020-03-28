/**
 * 
 */
package io.gojek.parkinglot.processor.data;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.gojek.parkinglot.exception.ParkingLotException;

/**
 * @author vaibhav.singh
 *
 */
public enum ParkingAction implements Action
{
	CREATE_PARKING_LOT("create_parking_lot", 1),
	PARK("park", 2),
	LEAVE("leave", 1),
	STATUS("status", 0),
	REG_NUMBER_FOR_CARS_WITH_COLOR("registration_numbers_for_cars_with_colour", 1),
	SLOTS_NUMBER_FOR_CARS_WITH_COLOR("slot_numbers_for_cars_with_colour", 1),
	SLOTS_NUMBER_FOR_REG_NUMBER("slot_number_for_registration_number", 1);

	int    noOfArgs = 0;
	String command;

	ParkingAction(String command, int args)
	{
		this.command = command;
		this.noOfArgs = args;
	}

	/**
	 * @return the command
	 */
	public String getCommand()
	{
		return command;
	}

	/**
	 * @return the noOfArgs
	 */
	public int getNoOfArgs()
	{
		return noOfArgs;
	}

	private static final Map<String, ParkingAction> NAME_MAP = Stream.of(values())
	                        .collect(Collectors.toMap(ParkingAction::getCommand, Function.identity()));

	private static Map<String, Integer> commandArgsMap = Stream.of(values())
	                        .collect(Collectors.toMap(ParkingAction::getCommand, ParkingAction::getNoOfArgs));

	public static ParkingAction getAction(String name) throws ParkingLotException
	{
		ParkingAction action = NAME_MAP.get(name);
		if (null == action)
		{
			throw new ParkingLotException("Unsupported Action");
		}
		return action;
	}

	public static int getActionArgs(String name) throws ParkingLotException
	{
		ParkingAction action = getAction(name);
		Integer argsCount = commandArgsMap.get(action.getCommand());
		if (null == argsCount)
		{
			throw new ParkingLotException("Unsupported Action");
		}
		return argsCount;
	}
}
