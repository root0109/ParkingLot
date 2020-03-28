/**
 * 
 */
package io.gojek.parkinglot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

import io.gojek.parkinglot.dao.ParkingDao;
import io.gojek.parkinglot.db.manager.InMemoryParkingManager;
import io.gojek.parkinglot.exception.ParkingKonstants;
import io.gojek.parkinglot.exception.ParkingLotException;
import io.gojek.parkinglot.model.ParkingLot;
import io.gojek.parkinglot.model.Vehicle;
import io.gojek.parkinglot.service.ParkingService;
import io.gojek.parkinglot.service.strategy.NearestFirstStrategy;
import io.gojek.parkinglot.service.strategy.ParkingStrategy;

/**
 * @author vaibhav.singh
 *
 */
public class ParkingServiceImpl implements ParkingService
{
	private ParkingDao<Vehicle> parkingDao = null;

	@Override
	public ParkingLot createParkingLot(int level, int capacity) throws ParkingLotException
	{
		if (parkingDao != null)
			throw new ParkingLotException(ParkingKonstants.PARKING_ALREADY_EXIST);
		List<Integer> parkingLevels = new ArrayList<>();
		List<Integer> capacityList = new ArrayList<>();
		List<ParkingStrategy> parkingStrategies = new ArrayList<>();
		parkingLevels.add(level);
		capacityList.add(capacity);
		parkingStrategies.add(new NearestFirstStrategy());
		this.parkingDao = new InMemoryParkingManager(parkingLevels, capacityList, parkingStrategies);
		System.out.println("Created parking lot with " + capacity + " slots");

		ParkingLot lot = new ParkingLot();
		String parkingId = UUID.randomUUID().toString();
		lot.setParkingId(parkingId);
		lot.setSlots(capacity);
		return lot;
	}

	@Override
	public int park(int level, Vehicle vehicle) throws ParkingLotException
	{
		int value = -1;
		validateParkingLot();
		try
		{
			value = parkingDao.parkVehicle(level, vehicle);
			if (value == ParkingKonstants.NOT_AVAILABLE)
				System.out.println("Sorry, parking lot is full");
			else if (value == ParkingKonstants.VEHICLE_ALREADY_PARKED)
				System.out.println("Sorry, vehicle is already parked.");
			else
				System.out.println("Allocated slot number: " + value);
		}
		catch (Exception e)
		{
			throw new ParkingLotException(ParkingKonstants.PROCESSING_ERROR, e);
		}
		return value;
	}

	@Override
	public boolean unPark(int level, int slotNumber) throws ParkingLotException
	{
		validateParkingLot();
		try
		{
			if (parkingDao.leaveVehicle(level, slotNumber))
				System.out.println("Slot number " + slotNumber + " is free");
			else
				System.out.println("Slot number is Empty Already.");
		}
		catch (Exception e)
		{
			throw new ParkingLotException(ParkingKonstants.INVALID_VALUE, e);
		}
		return true;
	}

	@Override
	public void getStatus(int level) throws ParkingLotException
	{
		validateParkingLot();
		try
		{
			System.out.println("Slot No.\tRegistration No.\tColor");
			List<String> statusList = parkingDao.getStatus(level);
			if (statusList.isEmpty())
				System.out.println("Sorry, parking lot is empty.");
			else
			{
				for (String statusSting : statusList)
				{
					System.out.println(statusSting);
				}
			}
		}
		catch (Exception e)
		{
			throw new ParkingLotException(ParkingKonstants.PROCESSING_ERROR, e);
		}
	}

	@Override
	public int getAvailableSlotsCount(int level) throws ParkingLotException
	{
		int value = 0;
		validateParkingLot();
		try
		{
			value = parkingDao.getAvailableSlotsCount(level);
		}
		catch (Exception e)
		{
			throw new ParkingLotException(ParkingKonstants.PROCESSING_ERROR, e);
		}
		return value;
	}

	@Override
	public List<String> getRegNumberForColor(int level, String color) throws ParkingLotException
	{
		validateParkingLot();
		List<String> registrationList = null;
		try
		{
			registrationList = parkingDao.getRegNumberForColor(level, color);
			if (registrationList.isEmpty())
				System.out.println("Not Found");
			else
				System.out.println(String.join(",", registrationList));
		}
		catch (Exception e)
		{
			throw new ParkingLotException(ParkingKonstants.PROCESSING_ERROR, e);
		}
		return registrationList;
	}

	@Override
	public List<Integer> getSlotNumbersFromColor(int level, String color) throws ParkingLotException
	{
		validateParkingLot();
		List<Integer> slotList = null;
		try
		{
			slotList = parkingDao.getSlotNumbersFromColor(level, color);
			if (slotList.isEmpty())
				System.out.println("Not Found");
			StringJoiner joiner = new StringJoiner(",");
			for (Integer slot : slotList)
			{
				joiner.add(slot + "");
			}
			System.out.println(joiner.toString());
		}
		catch (Exception e)
		{
			throw new ParkingLotException(ParkingKonstants.PROCESSING_ERROR, e);
		}
		return slotList;
	}

	@Override
	public int getSlotNoFromRegistrationNo(int level, String registrationNo) throws ParkingLotException
	{
		int value = -1;
		validateParkingLot();
		try
		{
			value = parkingDao.getSlotNoFromRegistrationNo(level, registrationNo);
			System.out.println(value != ParkingKonstants.NOT_FOUND ? value : "Not Found");
		}
		catch (Exception e)
		{
			throw new ParkingLotException(ParkingKonstants.PROCESSING_ERROR, e);
		}
		return value;
	}

	private void validateParkingLot() throws ParkingLotException
	{
		if (parkingDao == null)
		{
			throw new ParkingLotException(ParkingKonstants.PARKING_NOT_EXIST_ERROR);
		}
	}

	@Override
	public void doCleanUp()
	{
		if (parkingDao != null)
			parkingDao.doCleanup();
	}
}
