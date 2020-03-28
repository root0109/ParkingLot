/**
 * 
 */
package io.gojek.parkinglot.db.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.gojek.parkinglot.dao.ParkingDao;
import io.gojek.parkinglot.dao.ParkingLevelDao;
import io.gojek.parkinglot.model.Vehicle;
import io.gojek.parkinglot.service.strategy.ParkingStrategy;

/**
 * @author vaibhav.singh
 *
 */
public class InMemoryParkingManager implements ParkingDao<Vehicle>
{
	private Map<Integer, ParkingLevelDao<Vehicle>> levelParkingMap;

	public InMemoryParkingManager(List<Integer> parkingLevels, List<Integer> capacityList, List<ParkingStrategy> parkingStrategies)
	{
		if (levelParkingMap == null)
			levelParkingMap = new HashMap<>();
		for (int i = 0; i < parkingLevels.size(); i++)
		{
			levelParkingMap.put(parkingLevels.get(i),
			                new InMemoryParkingLevelManager(parkingLevels.get(i), capacityList.get(i), parkingStrategies.get(i)));

		}
	}

	@Override
	public int parkVehicle(int level, Vehicle vehicle)
	{
		return levelParkingMap.get(level).parkVehicle(vehicle);
	}

	@Override
	public boolean leaveVehicle(int level, int slotNumber)
	{
		return levelParkingMap.get(level).leaveVehicle(slotNumber);
	}

	@Override
	public List<String> getStatus(int level)
	{
		return levelParkingMap.get(level).getStatus();
	}

	public int getAvailableSlotsCount(int level)
	{
		return levelParkingMap.get(level).getAvailableSlotsCount();
	}

	@Override
	public List<String> getRegNumberForColor(int level, String color)
	{
		return levelParkingMap.get(level).getRegNumberForColor(color);
	}

	@Override
	public List<Integer> getSlotNumbersFromColor(int level, String color)
	{
		return levelParkingMap.get(level).getSlotNumbersFromColor(color);
	}

	@Override
	public int getSlotNoFromRegistrationNo(int level, String registrationNo)
	{
		return levelParkingMap.get(level).getSlotNoFromRegistrationNo(registrationNo);
	}

	@Override
	public void doCleanup()
	{
		for (ParkingLevelDao<Vehicle> levelParkingManager : levelParkingMap.values())
		{
			levelParkingManager.doCleanUp();
		}
		levelParkingMap = new HashMap<>();
	}
}
