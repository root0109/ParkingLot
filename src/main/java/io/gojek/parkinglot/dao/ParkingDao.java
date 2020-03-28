/**
 * 
 */
package io.gojek.parkinglot.dao;

import java.util.List;

import io.gojek.parkinglot.model.Vehicle;

/**
 * @author vaibhav.singh
 *
 */
public interface ParkingDao<T extends Vehicle>
{
	int parkVehicle(int level, T vehicle);

	boolean leaveVehicle(int level, int slotNumber);

	List<String> getStatus(int level);

	List<String> getRegNumberForColor(int level, String color);

	List<Integer> getSlotNumbersFromColor(int level, String colour);

	int getSlotNoFromRegistrationNo(int level, String registrationNo);

	int getAvailableSlotsCount(int level);

	public void doCleanup();
}
