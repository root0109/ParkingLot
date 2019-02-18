/**
 * 
 */
package io.gojek.parkinglot.service;

import java.util.Optional;

import io.gojek.parkinglot.exception.ParkingException;
import io.gojek.parkinglot.model.Vehicle;

/**
 * @author vaibhav
 *
 */
public interface ParkingService extends AbstractService
{
	/* ---- Actions ----- */
	public void createParkingLot(int level, int capacity) throws ParkingException;
	
	public Optional<Integer> park(int level, Vehicle vehicle) throws ParkingException;
	
	public void unPark(int level, int slotNumber) throws ParkingException;
	
	public void getStatus(int level) throws ParkingException;
	
	public Optional<Integer> getAvailableSlotsCount(int level) throws ParkingException;
	
	public void getRegNumberForColor(int level, String color) throws ParkingException;
	
	public void getSlotNumbersFromColor(int level, String colour) throws ParkingException;
	
	public int getSlotNoFromRegistrationNo(int level, String registrationNo) throws ParkingException;
	
	public void doCleanup();
}
