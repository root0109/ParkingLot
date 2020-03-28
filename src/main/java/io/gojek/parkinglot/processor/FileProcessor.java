/**
 * 
 */
package io.gojek.parkinglot.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import io.gojek.parkinglot.exception.ParkingKonstants;
import io.gojek.parkinglot.exception.ParkingLotException;
import io.gojek.parkinglot.processor.data.FileRequest;
import io.gojek.parkinglot.processor.data.Request;
import io.gojek.parkinglot.util.InputValidationUtil;
import io.gojek.parkinglot.util.ParkingUtil;

/**
 * @author vaibhav.singh
 *
 */
public class FileProcessor extends InteractiveProcessor
{
	@Override
	public boolean validateInput(Request request)
	{
		boolean valid = true;
		if (request instanceof FileRequest)
		{
			File inputFile = ((FileRequest) request).getFile();
			valid = inputFile.length() != 0;
			try (BufferedReader bufferReader = new BufferedReader(new FileReader(inputFile)))
			{
				String input = null;
				while ((input = bufferReader.readLine()) != null)
				{
					input = input.trim();
					if (!InputValidationUtil.validate(input))
					{
						valid = false;
						break;
					}
				}
			}
			catch (Exception e)
			{
				valid = false;
			}
		}
		return valid;
	}

	@Override
	public boolean execute(Request request) throws ParkingLotException
	{
		File inputFile = ((FileRequest) request).getFile();
		try (BufferedReader bufferReader = new BufferedReader(new FileReader(inputFile)))
		{
			String input = null;
			while ((input = bufferReader.readLine()) != null)
			{
				input = input.trim();
				try
				{
					Request interactiveRequest = ParkingUtil.getInteractiveRequest(input);
					super.execute(interactiveRequest);
				}
				catch (Exception e)
				{
					throw new ParkingLotException(e.getMessage());
				}
			}
		}
		catch (Exception e)
		{
			throw new ParkingLotException(ParkingKonstants.INVALID_FILE, e);
		}
		return true;
	}
}
