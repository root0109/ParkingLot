package io.gojek.parkinglot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.gojek.parkinglot.exception.ParkingLotException;
import io.gojek.parkinglot.processor.AbstractProcessor;
import io.gojek.parkinglot.processor.ProcessorFactory;
import io.gojek.parkinglot.processor.data.FileRequest;
import io.gojek.parkinglot.processor.data.ProgramRunType;
import io.gojek.parkinglot.processor.data.Request;
import io.gojek.parkinglot.service.ParkingService;
import io.gojek.parkinglot.service.impl.ParkingServiceImpl;

/**
 * Unit test for ParkingLot Runner.
 */
public class ParkingLotFileRunnerTest
{
	private static AbstractProcessor           processor                = null;
	private static ParkingService              parkingService           = null;
	private static final ByteArrayOutputStream outContent               = new ByteArrayOutputStream();
	private static final String                EMPTY_FILE_PATH          = "test_empty_file_inputs.txt";
	private static final String                DOES_NOT_EXIST_FILE_PATH = "does_not_exist_file_inputs.txt";
	private static final String                INVALID_INPUT_FILE       = "invalid_file_inputs.txt";
	private static final String                VALID_FILE               = "input/file_inputs.txt";

	@BeforeClass
	public static void setUp() throws ParkingLotException
	{
		processor = ProcessorFactory.createProcessor(ProgramRunType.FILE);
		parkingService = new ParkingServiceImpl();
		processor.setService(parkingService);
		System.setOut(new PrintStream(outContent));
	}

	@AfterClass
	public static void doCleanUp() throws ParkingLotException
	{
		parkingService.doCleanUp();
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}

	/*
	 * This case cannot be run in maven compile step as console out put can be matched in junit run only
	 * +ve Test case
	 */
	// @Test
	public void testInputFile() throws ParkingLotException
	{
		File file = new File(getClass().getClassLoader().getResource(VALID_FILE).getFile());
		Request request = new FileRequest(file);
		assertTrue(processor.validateInput(request));
		//@formatter:off
		final String expectedResult = "Created parking lot with 6 slots" + System.lineSeparator() +
								"Allocated slot number: 1" + System.lineSeparator() +
					            "Allocated slot number: 2" + System.lineSeparator() +
					            "Allocated slot number: 3" + System.lineSeparator() +
					            "Allocated slot number: 4" + System.lineSeparator() +
					            "Allocated slot number: 5" + System.lineSeparator() +
					            "Allocated slot number: 6" + System.lineSeparator() +
					            "Slot number 4 is free " + System.lineSeparator() +
					            "Slot No.    Registration No.   Color" + System.lineSeparator() +
					            "1           KA-01-HH-1234      White" + System.lineSeparator() +
					            "2           KA-01-HH-9999      White" + System.lineSeparator() +
					            "3           KA-01-BB-0001      Black" + System.lineSeparator() +
					            "5           KA-01-HH-2701      Blue" + System.lineSeparator() +
					            "6           KA-01-HH-3141      Black" + System.lineSeparator() +
					            "Allocated slot number: 4" + System.lineSeparator() +
					            "Sorry, parking lot is full" + System.lineSeparator() +
					            "KA-01-HH-1234,KA-01-HH-9999,KA-01-P-333" + System.lineSeparator() +
					            "1,2,4" + System.lineSeparator() + "6" + System.lineSeparator() + 
					            "Not Found"   + System.lineSeparator();
		//@formatter:on
		processor.execute(request);
		assertEquals(expectedResult.replaceAll("\\s+", ""), outContent.toString().replaceAll("\\s+", ""));
	}

	/*
	 * -ve Test case of Invalid File
	 */
	@Test
	public void testInputEmptyFile()
	{
		File file = new File(getClass().getClassLoader().getResource(EMPTY_FILE_PATH).getFile());
		assertFalse(processor.validateInput(new FileRequest(file)));
	}

	/*
	 * -ve Test case
	 */
	@Test(expected = NullPointerException.class)
	public void testInputFileDoesNotExist()
	{
		assertFalse(processor.validateInput(new FileRequest(new File(getClass().getClassLoader().getResource(DOES_NOT_EXIST_FILE_PATH).getFile()))));
	}

	/*
	 * -ve Test case
	 */
	@Test
	public void testInvalidInputFile()
	{
		File file = new File(getClass().getClassLoader().getResource(INVALID_INPUT_FILE).getFile());
		assertFalse(processor.validateInput(new FileRequest(file)));
	}
}
