/**
 * 
 */
package io.gojek.parkinglot.service;

import java.util.List;

import io.gojek.parkinglot.exception.ParkingLotException;
import io.gojek.parkinglot.model.ParkingLot;
import io.gojek.parkinglot.model.Vehicle;
import io.gojek.parkinglot.service.strategy.ParkingStrategy;

/**
 * @author vaibhav.singh
 *
 */
public interface MultiParkingService extends AbstractService
{
	public ParkingLot createParkingLot(int level, int capacity) throws ParkingLotException;

	public int park(ParkingLot parkingLot, int level, Vehicle vehicle) throws ParkingLotException;

	public int park(int level, Vehicle vehicle, ParkingStrategy parkingStrategy) throws ParkingLotException;

	public boolean unPark(int level, int slotNumber) throws ParkingLotException;

	public void getStatus(ParkingLot parkingLot, int level) throws ParkingLotException;

	public int getAvailableSlotsCount(ParkingLot parkingLot, int level) throws ParkingLotException;

	public List<String> getRegNumberForColor(int level, String color) throws ParkingLotException;

	public List<Integer> getSlotNumbersFromColor(int level, String colour) throws ParkingLotException;

	public int getSlotNoFromRegistrationNo(int level, String registrationNo) throws ParkingLotException;

	public void doCleanUp();
}
