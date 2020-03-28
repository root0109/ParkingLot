/**
 * 
 */
package io.gojek.parkinglot.db.test;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.gojek.parkinglot.exception.ParkingLotException;
import io.gojek.parkinglot.model.ParkingLot;
import io.gojek.parkinglot.service.MultiParkingService;
import io.gojek.parkinglot.service.impl.MultiParkingServiceImpl;

/**
 * @author 91845
 *
 */
public class MultiParkingTest
{
	private final static int           LEVEL               = 1;
	private static MultiParkingService multiParkingService = new MultiParkingServiceImpl();

	@BeforeClass
	public static void setUp() throws ParkingLotException
	{
		multiParkingService = new MultiParkingServiceImpl();
	}

	@AfterClass
	public static void doCleanUp() throws ParkingLotException
	{
		multiParkingService.doCleanUp();
	}

	/*
	 * +ve Test case
	 */
	@Test
	public void t2_testAfterCreationProcess() throws ParkingLotException
	{
		ParkingLot parkingLot1 = multiParkingService.createParkingLot(LEVEL, 6);
		ParkingLot parkingLot2 = multiParkingService.createParkingLot(LEVEL, 6);
		ParkingLot parkingLot3 = multiParkingService.createParkingLot(LEVEL, 6);
		assertTrue(parkingLot1 != null);
		assertTrue(parkingLot2 != null);
		assertTrue(parkingLot3 != null);
		assertTrue("EAch PArking Lot ID has to be unique", parkingLot1.getParkingId() != parkingLot2.getParkingId());
		assertTrue("EAch PArking Lot ID has to be unique", parkingLot2.getParkingId() != parkingLot3.getParkingId());
	}

	/*
	 * -ve Test case
	 */
	@Test
	public void t2_testAfterCreationProcess1() throws ParkingLotException
	{
		ParkingLot parkingLot1 = multiParkingService.createParkingLot(LEVEL, 6);
		ParkingLot parkingLot2 = multiParkingService.createParkingLot(LEVEL, 7);
		ParkingLot parkingLot3 = multiParkingService.createParkingLot(LEVEL, 8);
		assertTrue(parkingLot1 != null);
		assertTrue(parkingLot2 != null);
		assertTrue(parkingLot3 != null);
		assertTrue("EAch PArking Lot ID has to be unique", parkingLot1.getParkingId() != parkingLot2.getParkingId());
		assertTrue("EAch PArking Lot ID has to be unique", parkingLot2.getParkingId() != parkingLot3.getParkingId());
	}

}
