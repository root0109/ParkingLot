/**
 * 
 */
package io.gojek.parkinglot.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vaibhav
 *
 */
public class CommandInputMap
{
	private static volatile Map<String, Integer> commandsParameterMap = new HashMap<String, Integer>();
	
	static
	{
		commandsParameterMap.put(Constants.CREATE_PARKING_LOT, 1);
		commandsParameterMap.put(Constants.PARK, 2);
		commandsParameterMap.put(Constants.LEAVE, 1);
		commandsParameterMap.put(Constants.STATUS, 0);
		commandsParameterMap.put(Constants.REG_NUMBER_FOR_CARS_WITH_COLOR, 1);
		commandsParameterMap.put(Constants.SLOTS_NUMBER_FOR_CARS_WITH_COLOR, 1);
		commandsParameterMap.put(Constants.SLOTS_NUMBER_FOR_REG_NUMBER, 1);
	}
	
	/**
	 * @return the commandsParameterMap
	 */
	public static Map<String, Integer> getCommandsParameterMap()
	{
		return commandsParameterMap;
	}
	
	/**
	 * @param commandsParameterMap
	 *            the commandsParameterMap to set
	 */
	public static void addCommand(String command, int parameterCount)
	{
		commandsParameterMap.put(command, parameterCount);
	}
	
}
