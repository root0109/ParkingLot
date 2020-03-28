/**
 * 
 */
package io.gojek.parkinglot.util;

import io.gojek.parkinglot.processor.data.ParkingAction;

/**
 * @author vaibhav.singh
 *
 */
public final class InputValidationUtil
{
	private InputValidationUtil()
	{
	}

	public static boolean validate(String input)
	{
		// Split the input string to validate command and input value
		boolean valid = true;
		try
		{
			String[] inputs = input.trim().split(" ");
			int params = ParkingAction.getActionArgs(inputs[0]);
			switch (inputs.length)
			{
				case 1 :
					if (params != 0) // e.g status --> inputs = 1
						valid = false;
					break;
				case 2 :
					if (params != 1) // create_parking_lot 6 --> inputs = 2
						valid = false;
					break;
				case 3 :
					if (params != 2) // park KA-01-P-333 White --> inputs = 3
						valid = false;
					break;
				default:
					valid = false;
			}
		}
		catch (Exception e)
		{
			valid = false;
		}
		return valid;
	}
}
