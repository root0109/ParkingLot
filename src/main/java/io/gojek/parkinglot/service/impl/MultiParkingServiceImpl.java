/**
 * 
 */
package io.gojek.parkinglot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.gojek.parkinglot.exception.ParkingLotException;
import io.gojek.parkinglot.model.ParkingLot;
import io.gojek.parkinglot.model.Vehicle;
import io.gojek.parkinglot.service.MultiParkingService;
import io.gojek.parkinglot.service.ParkingService;
import io.gojek.parkinglot.service.strategy.ParkingStrategy;

/**
 * @author vaibhav.singh
 *
 */
public class MultiParkingServiceImpl implements MultiParkingService
{
	// parking id vs parking service Map
	private final Map<String, ParkingService> parkingLotServiceMapping = new HashMap<>();

	@Override
	public ParkingLot createParkingLot(int level, int capacity) throws ParkingLotException
	{
		ParkingService parkingService = new ParkingServiceImpl();
		ParkingLot lot = parkingService.createParkingLot(level, capacity);
		parkingLotServiceMapping.put(lot.getParkingId(), parkingService);
		return lot;
	}

	@Override
	public int park(ParkingLot parkingLot, int level, Vehicle vehicle) throws ParkingLotException
	{
		return 0;
	}

	@Override
	public int park(int level, Vehicle vehicle, ParkingStrategy parkingStrategy) throws ParkingLotException
	{
		return 0;
	}

	@Override
	public boolean unPark(int level, int slotNumber) throws ParkingLotException
	{
		return false;
	}

	@Override
	public void getStatus(ParkingLot parkingLot, int level) throws ParkingLotException
	{

	}

	@Override
	public int getAvailableSlotsCount(ParkingLot parkingLot, int level) throws ParkingLotException
	{
		return 0;
	}

	@Override
	public List<String> getRegNumberForColor(int level, String color) throws ParkingLotException
	{
		return null;
	}

	@Override
	public List<Integer> getSlotNumbersFromColor(int level, String colour) throws ParkingLotException
	{
		return null;
	}

	@Override
	public int getSlotNoFromRegistrationNo(int level, String registrationNo) throws ParkingLotException
	{
		return 0;
	}

	@Override
	public void doCleanUp()
	{
		for (ParkingService parkingService : parkingLotServiceMapping.values())
		{
			parkingService.doCleanUp();
		}
	}
}
