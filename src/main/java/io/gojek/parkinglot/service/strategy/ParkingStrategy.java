package io.gojek.parkinglot.service.strategy;

/**
 * 
 * @author vaibhav.singh
 *
 */
public interface ParkingStrategy
{
	boolean add(int i);

	boolean removeSlot(int availableSlot);

	int getSlot();

}
