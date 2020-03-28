/**
 * 
 */
package io.gojek.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import io.gojek.parkinglot.exception.ParkingKonstants;
import io.gojek.parkinglot.exception.ParkingLotException;
import io.gojek.parkinglot.processor.AbstractProcessor;
import io.gojek.parkinglot.processor.ProcessorFactory;
import io.gojek.parkinglot.processor.data.FileRequest;
import io.gojek.parkinglot.processor.data.InteractiveRequest;
import io.gojek.parkinglot.processor.data.ProgramRunType;
import io.gojek.parkinglot.processor.data.Request;
import io.gojek.parkinglot.service.AbstractService;
import io.gojek.parkinglot.service.impl.ParkingServiceImpl;

/**
 * @author vaibhav.singh
 *
 */
public class ParkingLotRunner
{
	public static void main(String[] args)
	{
		AbstractService abstractService = new ParkingServiceImpl();
		switch (args.length)
		{
			case 0: // Interactive: command-line input/output
			{
				printUsage();
				processInteractive(abstractService);
				break;
			}
			case 1:// File input/output
			{
				processFile(args, abstractService);
				break;
			}
			default:
				System.out.println("Invalid input. Usage Style: java -jar <jar_file_path> <input_file_path>");
		}
	}

	/**
	 * @param args
	 * @param abstractService
	 */
	private static void processFile(String[] args, AbstractService abstractService)
	{
		AbstractProcessor processor = ProcessorFactory.createProcessor(ProgramRunType.FILE);
		processor.setService(abstractService);
		File inputFile = new File(args[0]);
		Request request = new FileRequest(inputFile);

		if (!processor.validateInput(request))
		{
			System.out.println(ParkingKonstants.INVALID_REQUEST);
			System.exit(0);
		}
		else
		{
			try
			{
				processor.execute(request);
			}
			catch (ParkingLotException e)
			{
				System.out.println(e.getMessage());
			}
			catch (Exception e)
			{
				System.out.println(ParkingKonstants.PROCESSING_ERROR);
			}
		}
	}

	/**
	 * @param abstractService
	 * @param bufferReader
	 * @return
	 */
	private static void processInteractive(AbstractService abstractService)
	{
		AbstractProcessor processor;
		String input;
		processor = ProcessorFactory.createProcessor(ProgramRunType.INTERACTIVE);
		processor.setService(abstractService);
		System.out.println("Please Enter 'exit' to end Execution");
		System.out.println("Input:");
		while (true)
		{
			try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in)))
			{
				input = bufferReader.readLine().trim();
				Request request = new InteractiveRequest(input);
				if (input.equalsIgnoreCase("exit"))
				{
					break;
				}
				else
				{
					if (!processor.validateInput(request))
					{
						System.out.println(ParkingKonstants.INVALID_REQUEST);
					}
					else
						processor.execute(request);
				}
			}
			catch (ParkingLotException e)
			{
				System.out.println(e.getMessage());
			}
			catch (Exception e)
			{
				System.out.println(ParkingKonstants.PROCESSING_ERROR);
				System.exit(0);
			}
		}
	}

	private static void printUsage()
	{
		StringBuffer buffer = new StringBuffer();
		buffer = buffer.append("--------------Please Enter one of the below commands. {variable} to be replaced -----------------------")
		                .append("\n");
		buffer = buffer.append("A) For creating parking lot of size n               ---> create_parking_lot {capacity}").append("\n");
		buffer = buffer.append("B) To park a car                                    ---> park <<car_number>> {car_clour}").append("\n");
		buffer = buffer.append("C) Remove(Unpark) car from parking                  ---> leave {slot_number}").append("\n");
		buffer = buffer.append("D) Print status of parking slot                     ---> status").append("\n");
		buffer = buffer.append("E) Get cars registration no for the given car color ---> registration_numbers_for_cars_with_colour {car_color}")
		                .append("\n");
		buffer = buffer.append("F) Get slot numbers for the given car color         ---> slot_numbers_for_cars_with_colour {car_color}").append("\n");
		buffer = buffer.append("G) Get slot number for the given car number         ---> slot_number_for_registration_number {car_number}")
		                .append("\n");
		System.out.println(buffer.toString());
	}
}
