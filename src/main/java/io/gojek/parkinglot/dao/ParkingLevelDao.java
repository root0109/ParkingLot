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
public interface ParkingLevelDao<T extends Vehicle>
{
	int parkVehicle(T vehicle);

	boolean leaveVehicle(int slotNumber);

	List<String> getStatus();

	List<String> getRegNumberForColor(String color);

	List<Integer> getSlotNumbersFromColor(String colour);

	int getSlotNoFromRegistrationNo(String registrationNo);

	int getAvailableSlotsCount();

	void doCleanUp();
}
