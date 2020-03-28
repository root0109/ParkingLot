/**
 * 
 */
package io.gojek.parkinglot.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.gojek.parkinglot.exception.ParkingLotException;
import io.gojek.parkinglot.model.Car;
import io.gojek.parkinglot.service.impl.ParkingServiceImpl;

/**
 * @author vaibhav.singh
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingServiceTest
{
	private static final int      parkingLevel   = 1;
	private static ParkingService parkingService = null;

	@BeforeClass
	public static void setUp() throws ParkingLotException
	{
		parkingService = new ParkingServiceImpl();
	}

	@AfterClass
	public static void doCleanUp() throws ParkingLotException
	{
		parkingService.doCleanUp();
	}

	/*
	 * -ve Test case
	 */
	@Test(expected = ParkingLotException.class)
	public void t1_testAlreadyCreatedEmptyParkingLot() throws Exception
	{
		assertNotNull(parkingService.getAvailableSlotsCount(parkingLevel));
	}

	/*
	 * +ve Test case
	 */
	@Test
	public void t2_testAfterCreationProcess() throws ParkingLotException
	{
		assertTrue(parkingService.createParkingLot(parkingLevel, 6).getParkingId() != null);
		assertTrue(parkingService.getAvailableSlotsCount(parkingLevel) == 6);
	}

	/*
	 * +ve Test case
	 */
	@Test
	public void t3_testPark() throws ParkingLotException
	{
		assertTrue(parkingService.park(parkingLevel, new Car("DL-12-AA-9999", "White")) > 0);
		assertTrue(parkingService.park(parkingLevel, new Car("DL-22-AA-9999", "Black")) > 0);
		assertTrue(parkingService.park(parkingLevel, new Car("DL-32-AA-9999", "White")) > 0);
		assertTrue(parkingService.park(parkingLevel, new Car("DL-42-AA-9999", "Red")) > 0);
		assertTrue(parkingService.park(parkingLevel, new Car("DL-52-AA-9999", "White")) > 0);
		assertTrue(parkingService.park(parkingLevel, new Car("DL-62-AA-9999", "White")) > 0);
	}

	/*
	 * -ve Test case
	 */
	@Test
	public void t4_testParkingLotIsFull() throws Exception
	{
		assertTrue(parkingService.getAvailableSlotsCount(parkingLevel) == 0);
	}

	/*
	 * -ve Test case
	 */
	@Test
	public void t5_testUnParkVehicle() throws Exception
	{
		assertTrue(parkingService.unPark(parkingLevel, 6));
	}

	/*
	 * +ve Test case
	 */
	@Test
	public void t6_testGetSlotsByColor() throws ParkingLotException
	{
		assertTrue(!parkingService.getSlotNumbersFromColor(parkingLevel, "White").isEmpty());
	}

	/*
	 * +ve Test case
	 */
	@Test
	public void t7_testGetRegNoByColor() throws ParkingLotException
	{
		assertTrue(!parkingService.getRegNumberForColor(parkingLevel, "White").isEmpty());
	}

	/*
	 * -ve Test case
	 */
	@Test
	public void t8_testGetRegNoByColorNotFound() throws ParkingLotException
	{
		assertTrue(parkingService.getRegNumberForColor(parkingLevel, "Green").isEmpty());
	}

	/*
	 * +ve Test case
	 */
	@Test
	public void t9_testSlotByRegNo() throws ParkingLotException
	{
		assertTrue(parkingService.getSlotNoFromRegistrationNo(parkingLevel, "DL-12-AA-9999") == 1);
	}
}
