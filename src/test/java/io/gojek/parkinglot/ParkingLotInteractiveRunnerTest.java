package io.gojek.parkinglot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.gojek.parkinglot.exception.ParkingLotException;
import io.gojek.parkinglot.processor.AbstractProcessor;
import io.gojek.parkinglot.processor.ProcessorFactory;
import io.gojek.parkinglot.processor.data.InteractiveRequest;
import io.gojek.parkinglot.processor.data.ProgramRunType;
import io.gojek.parkinglot.processor.data.Request;
import io.gojek.parkinglot.service.ParkingService;
import io.gojek.parkinglot.service.impl.ParkingServiceImpl;

/**
 * Unit test for ParkingLot Runner.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingLotInteractiveRunnerTest
{
	private static AbstractProcessor processor      = null;
	private static ParkingService    parkingService = null;

	@BeforeClass
	public static void setUp() throws ParkingLotException
	{
		processor = ProcessorFactory.createProcessor(ProgramRunType.INTERACTIVE);
		parkingService = new ParkingServiceImpl();
		processor.setService(parkingService);
	}

	@AfterClass
	public static void doCleanUp() throws ParkingLotException
	{
		parkingService.doCleanUp();
	}

	/*
	 * +ve Test case
	 */
	@Test
	public void t1_testFirstTimeCreateProcess() throws ParkingLotException
	{
		Request request = new InteractiveRequest("create_parking_lot 6");
		assertTrue(processor.validateInput(request));
		assertTrue(processor.execute(request));
	}

	/*
	 * +ve Test case
	 */
	@Test
	public void t2_testCommands() throws Exception
	{
		Request request = new InteractiveRequest(" park KA-01-HH-1234 White");
		assertTrue(processor.validateInput(request));
		assertTrue(processor.execute(request));
		request = new InteractiveRequest("park KA-01-HH-9999 White");
		assertTrue(processor.validateInput(request));
		assertTrue(processor.execute(request));
		request = new InteractiveRequest("leave 4");
		assertTrue(processor.validateInput(request));
		assertTrue(processor.execute(request));
		request = new InteractiveRequest("status");
		assertTrue(processor.validateInput(request));
		assertTrue(processor.execute(request));
		request = new InteractiveRequest(" registration_numbers_for_cars_with_colour White");
		assertTrue(processor.validateInput(request));
		assertTrue(processor.execute(request));
		request = new InteractiveRequest("slot_number_for_registration_number KA-01-HH-3141");
		assertTrue(processor.validateInput(request));
		assertTrue(processor.execute(request));
		request = new InteractiveRequest("slot_number_for_registration_number MH-04-AY-1111");
		assertTrue(processor.validateInput(request));
		assertTrue(processor.execute(request));
	}

	/*
	 * -ve Test case
	 */
	@Test(expected = ParkingLotException.class)
	public void t3_testUnknownCommand() throws Exception
	{
		Request request = new InteractiveRequest("exit");
		assertFalse(processor.validateInput(request));
		assertTrue(processor.execute(request));
	}

	/*
	 * -ve Test case
	 */
	@Test(expected = NullPointerException.class)
	public void t3_testNullCommand() throws Exception
	{
		Request request = new InteractiveRequest(null);
		assertFalse(processor.validateInput(request));
		assertTrue(processor.execute(request));
	}
}
