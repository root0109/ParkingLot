/**
 * 
 */
package io.gojek.parkinglot.db.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import io.gojek.parkinglot.dao.ParkingLevelDao;
import io.gojek.parkinglot.exception.ParkingKonstants;
import io.gojek.parkinglot.model.Vehicle;
import io.gojek.parkinglot.service.strategy.NearestFirstStrategy;
import io.gojek.parkinglot.service.strategy.ParkingStrategy;

/**
 * @author vaibhav.singh
 *
 */
public class InMemoryParkingLevelManager implements ParkingLevelDao<Vehicle>
{
	// For Multilevel Parking lot - 0 -> Ground floor 1 -> First Floor etc
	private AtomicInteger	level			= new AtomicInteger(0);
	private AtomicInteger	capacity		= new AtomicInteger();
	private AtomicInteger	availability	= new AtomicInteger();
	// Allocation Strategy for parking
	private ParkingStrategy parkingStrategy;
	// this is per level - slot - vehicle
	private Map<Integer, Optional<Vehicle>> slotVehicleMap;

	public InMemoryParkingLevelManager(int level, int capacity, ParkingStrategy parkingStrategy)
	{
		this.level.set(level);
		this.capacity.set(capacity);
		this.availability.set(capacity);
		this.parkingStrategy = parkingStrategy;
		if (parkingStrategy == null)
			parkingStrategy = new NearestFirstStrategy();
		slotVehicleMap = new ConcurrentHashMap<>();
		for (int i = 1; i <= capacity; i++)
		{
			slotVehicleMap.put(i, Optional.empty());
			parkingStrategy.add(i);
		}
	}

	@Override
	public int parkVehicle(Vehicle vehicle)
	{
		int availableSlot;
		if (availability.get() == 0)
		{
			return ParkingKonstants.NOT_AVAILABLE;
		}
		else
		{
			availableSlot = parkingStrategy.getSlot();
			if (slotVehicleMap.containsValue(Optional.of(vehicle)))
				return ParkingKonstants.VEHICLE_ALREADY_PARKED;

			slotVehicleMap.put(availableSlot, Optional.of(vehicle));
			availability.decrementAndGet();
			parkingStrategy.removeSlot(availableSlot);
		}
		return availableSlot;
	}

	@Override
	public boolean leaveVehicle(int slotNumber)
	{
		if (!slotVehicleMap.get(slotNumber).isPresent()) // Slot already empty
			return false;
		availability.incrementAndGet();
		parkingStrategy.add(slotNumber);
		slotVehicleMap.put(slotNumber, Optional.empty());
		return true;
	}

	@Override
	public List<String> getStatus()
	{
		List<String> statusList = new ArrayList<>();
		for (int i = 1; i <= capacity.get(); i++)
		{
			Optional<Vehicle> vehicle = slotVehicleMap.get(i);
			if (vehicle.isPresent())
			{
				statusList.add(i + "\t\t" + vehicle.get().getLicensePlateNo() + "\t\t" + vehicle.get().getColor());
			}
		}
		return statusList;
	}

	public int getAvailableSlotsCount()
	{
		return availability.get();
	}

	@Override
	public List<String> getRegNumberForColor(String color)
	{
		List<String> statusList = new ArrayList<>();
		for (int i = 1; i <= capacity.get(); i++)
		{
			Optional<Vehicle> vehicle = slotVehicleMap.get(i);
			if (vehicle.isPresent() && color.equalsIgnoreCase(vehicle.get().getColor()))
			{
				statusList.add(vehicle.get().getLicensePlateNo());
			}
		}
		return statusList;
	}

	@Override
	public List<Integer> getSlotNumbersFromColor(String colour)
	{
		List<Integer> slotList = new ArrayList<>();
		for (int i = 1; i <= capacity.get(); i++)
		{
			Optional<Vehicle> vehicle = slotVehicleMap.get(i);
			if (vehicle.isPresent() && colour.equalsIgnoreCase(vehicle.get().getColor()))
			{
				slotList.add(i);
			}
		}
		return slotList;
	}

	@Override
	public int getSlotNoFromRegistrationNo(String registrationNo)
	{
		int result = ParkingKonstants.NOT_FOUND;
		for (int i = 1; i <= capacity.get(); i++)
		{
			Optional<Vehicle> vehicle = slotVehicleMap.get(i);
			if (vehicle.isPresent() && registrationNo.equalsIgnoreCase(vehicle.get().getLicensePlateNo()))
			{
				result = i;
			}
		}
		return result;
	}

	@Override
	public void doCleanUp()
	{
		this.level = new AtomicInteger();
		this.capacity = new AtomicInteger();
		this.availability = new AtomicInteger();
		this.parkingStrategy = null;
		slotVehicleMap = new ConcurrentHashMap<>();
	}
}
