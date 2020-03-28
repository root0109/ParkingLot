/**
 * 
 */
package io.gojek.parkinglot.db.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.gojek.parkinglot.dao.ParkingDao;
import io.gojek.parkinglot.db.manager.InMemoryParkingManager;
import io.gojek.parkinglot.exception.ParkingKonstants;
import io.gojek.parkinglot.exception.ParkingLotException;
import io.gojek.parkinglot.model.Car;
import io.gojek.parkinglot.model.Vehicle;
import io.gojek.parkinglot.service.strategy.NearestFirstStrategy;

/**
 * @author vaibhav.singh
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingTest
{
	private static final int			parkingLevel	= 1;
	private static ParkingDao<Vehicle>	parkingDao;

	@BeforeClass
	public static void init()
	{
		parkingDao = new InMemoryParkingManager(Arrays.asList(parkingLevel), Arrays.asList(6), Arrays.asList(new NearestFirstStrategy()));
		parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-1233", "white"));
	}

	@AfterClass
	public static void cleanup()
	{
		parkingDao.doCleanup();
	}

	@Test
	public void t01_testParkVehicleSecondSlot() throws ParkingLotException
	{
		assertTrue(parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-1232", "black")) == 2);
	}

	@Test
	public void t02_testVehicleAlreadyExist() throws ParkingLotException
	{
		assertTrue(parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-1233", "white")) == ParkingKonstants.VEHICLE_ALREADY_PARKED);
	}

	@Test
	public void t03_testVehicleNotAvailable() throws ParkingLotException
	{
		parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-1234", "green"));
		parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-1235", "white"));
		parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-1236", "white"));
		parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-1237", "white"));
		assertTrue(parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-1233", "white")) == ParkingKonstants.NOT_AVAILABLE);
	}

	@Test
	public void t04_parkVehicle_ParkingFull_LeaveParking_ParkVehicle() throws ParkingLotException
	{
		assertTrue(parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-1233", "white")) == ParkingKonstants.NOT_AVAILABLE);
		assertTrue(parkingDao.leaveVehicle(parkingLevel, 6));
		assertTrue(parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-1133", "white")) == 6);
	}

	@Test
	public void t05_leaveVehicle() throws ParkingLotException
	{
		assertTrue(parkingDao.leaveVehicle(parkingLevel, 6));
	}

	@Test
	public void t06_getStatus() throws ParkingLotException
	{
		parkingDao.leaveVehicle(parkingLevel, 1);
		parkingDao.leaveVehicle(parkingLevel, 2);
		parkingDao.leaveVehicle(parkingLevel, 3);
		parkingDao.leaveVehicle(parkingLevel, 4);
		parkingDao.leaveVehicle(parkingLevel, 5);
		parkingDao.leaveVehicle(parkingLevel, 6);
		parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-1234", "White"));
		parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-3141", "Black"));
		parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-9999", "White"));
		assertEquals("parking status", "1		KA-01-HH-1234		White,2		KA-01-HH-3141		Black,3		KA-01-HH-9999		White",
		                String.join(",", parkingDao.getStatus(parkingLevel)));
	}

	@Test
	public void t07_getStatusEmptyParkingLot() throws ParkingLotException
	{
		parkingDao.leaveVehicle(parkingLevel, 1);
		parkingDao.leaveVehicle(parkingLevel, 2);
		parkingDao.leaveVehicle(parkingLevel, 3);
		parkingDao.leaveVehicle(parkingLevel, 4);
		parkingDao.leaveVehicle(parkingLevel, 5);
		parkingDao.leaveVehicle(parkingLevel, 6);
		assertEquals(String.join(",", parkingDao.getStatus(parkingLevel)), "");
	}

	@Test
	public void t08_getVehicleRegNosForColor() throws ParkingLotException
	{
		parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-1234", "White"));
		parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-3141", "Black"));
		parkingDao.parkVehicle(parkingLevel, new Car("KA-01-HH-1111", "White"));
		assertEquals("KA-01-HH-1234,KA-01-HH-1111", String.join(",", parkingDao.getRegNumberForColor(parkingLevel, "White")));
	}

	@Test
	public void t09_getSlotNosForVehicleColor() throws ParkingLotException
	{
		assertTrue(parkingDao.getSlotNumbersFromColor(parkingLevel, "Black").contains(2));
	}

	@Test
	public void t10_getSlotNosForVehicleColorNotFound() throws ParkingLotException
	{
		assertTrue(parkingDao.getSlotNumbersFromColor(parkingLevel, "Red").isEmpty());
	}

	@Test
	public void t11_getSlotNoForVehicle() throws ParkingLotException
	{
		assertEquals("Get slot for vehicle", 2, parkingDao.getSlotNoFromRegistrationNo(parkingLevel, "KA-01-HH-3141"));
	}

	@Test
	public void t12_getSlotNoForVehicleNotFound() throws ParkingLotException
	{
		assertEquals(parkingDao.getSlotNoFromRegistrationNo(parkingLevel, "OD-01-HH-1134"), ParkingKonstants.NOT_FOUND);
	}
}
