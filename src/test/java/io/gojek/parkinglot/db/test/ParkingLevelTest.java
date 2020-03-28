/**
 * 
 */
package io.gojek.parkinglot.db.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.gojek.parkinglot.dao.ParkingLevelDao;
import io.gojek.parkinglot.db.manager.InMemoryParkingLevelManager;
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
public class ParkingLevelTest
{
	private static final int				parkingLevel	= 1;
	private static ParkingLevelDao<Vehicle>	parkingLevelDao;

	@BeforeClass
	public static void init()
	{
		parkingLevelDao = new InMemoryParkingLevelManager(parkingLevel, 6, new NearestFirstStrategy());
		parkingLevelDao.parkVehicle(new Car("KA-01-HH-1233", "white"));
	}

	@AfterClass
	public static void cleanup()
	{
		parkingLevelDao.doCleanUp();
	}

	@Test
	public void t01_testParkVehicleSecondSlot() throws ParkingLotException
	{
		assertTrue(parkingLevelDao.parkVehicle(new Car("KA-01-HH-1232", "black")) == 2);
	}

	@Test
	public void t02_testVehicleAlreadyExist() throws ParkingLotException
	{
		assertTrue(parkingLevelDao.parkVehicle(new Car("KA-01-HH-1233", "white")) == ParkingKonstants.VEHICLE_ALREADY_PARKED);
	}

	@Test
	public void t03_testVehicleNotAvailable() throws ParkingLotException
	{
		parkingLevelDao.parkVehicle(new Car("KA-01-HH-1234", "green"));
		parkingLevelDao.parkVehicle(new Car("KA-01-HH-1235", "white"));
		parkingLevelDao.parkVehicle(new Car("KA-01-HH-1236", "white"));
		parkingLevelDao.parkVehicle(new Car("KA-01-HH-1237", "white"));
		assertTrue(parkingLevelDao.parkVehicle(new Car("KA-01-HH-1233", "white")) == ParkingKonstants.NOT_AVAILABLE);
	}

	@Test
	public void t04_parkVehicle_ParkingFull_LeaveParking_ParkVehicle() throws ParkingLotException
	{
		assertTrue(parkingLevelDao.parkVehicle(new Car("KA-01-HH-1233", "white")) == ParkingKonstants.NOT_AVAILABLE);
		assertTrue(parkingLevelDao.leaveVehicle(6));
		assertTrue(parkingLevelDao.parkVehicle(new Car("KA-01-HH-1133", "white")) == 6);
	}

	@Test
	public void t05_leaveVehicle() throws ParkingLotException
	{
		assertTrue(parkingLevelDao.leaveVehicle(6));
	}

	@Test
	public void t06_getStatus() throws ParkingLotException
	{
		parkingLevelDao.leaveVehicle(1);
		parkingLevelDao.leaveVehicle(2);
		parkingLevelDao.leaveVehicle(3);
		parkingLevelDao.leaveVehicle(4);
		parkingLevelDao.leaveVehicle(5);
		parkingLevelDao.leaveVehicle(6);
		parkingLevelDao.parkVehicle(new Car("KA-01-HH-1234", "White"));
		parkingLevelDao.parkVehicle(new Car("KA-01-HH-3141", "Black"));
		parkingLevelDao.parkVehicle(new Car("KA-01-HH-9999", "White"));
		assertEquals("parking status", "1		KA-01-HH-1234		White,2		KA-01-HH-3141		Black,3		KA-01-HH-9999		White",
		                String.join(",", parkingLevelDao.getStatus()));
	}

	@Test
	public void t07_getStatusEmptyParkingLot() throws ParkingLotException
	{
		parkingLevelDao.leaveVehicle(1);
		parkingLevelDao.leaveVehicle(2);
		parkingLevelDao.leaveVehicle(3);
		parkingLevelDao.leaveVehicle(4);
		parkingLevelDao.leaveVehicle(5);
		parkingLevelDao.leaveVehicle(6);
		assertEquals(String.join(",", parkingLevelDao.getStatus()), "");
	}

	@Test
	public void t08_getVehicleRegNosForColor() throws ParkingLotException
	{
		parkingLevelDao.parkVehicle(new Car("KA-01-HH-1234", "White"));
		parkingLevelDao.parkVehicle(new Car("KA-01-HH-3141", "Black"));
		parkingLevelDao.parkVehicle(new Car("KA-01-HH-1111", "White"));
		assertEquals("KA-01-HH-1234,KA-01-HH-1111", String.join(",", parkingLevelDao.getRegNumberForColor("White")));
	}

	@Test
	public void t09_getSlotNosForVehicleColor() throws ParkingLotException
	{
		assertTrue(parkingLevelDao.getSlotNumbersFromColor("Black").contains(2));
	}

	@Test
	public void t10_getSlotNosForVehicleColorNotFound() throws ParkingLotException
	{
		assertTrue(parkingLevelDao.getSlotNumbersFromColor("Red").isEmpty());
	}

	@Test
	public void t11_getSlotNoForVehicle() throws ParkingLotException
	{
		assertEquals("Get slot for vehicle", 2, parkingLevelDao.getSlotNoFromRegistrationNo("KA-01-HH-3141"));
	}

	@Test
	public void t12_getSlotNoForVehicleNotFound() throws ParkingLotException
	{
		assertEquals(parkingLevelDao.getSlotNoFromRegistrationNo("OD-01-HH-1134"), ParkingKonstants.NOT_FOUND);
	}
}
