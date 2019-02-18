/**
 * 
 */
package io.gojek.parkinglot.model.strategy;

import java.util.TreeSet;

/**
 * @author vaibhav
 *
 */
public class NearestFirstParkingStrategy implements ParkingStrategy
{
	private TreeSet<Integer> freeSlots;
	
	public NearestFirstParkingStrategy()
	{
		freeSlots = new TreeSet<Integer>();
	}
	
	@Override
	public void add(int i)
	{
		freeSlots.add(i);
	}
	
	@Override
	public int getSlot()
	{
		return freeSlots.first();
	}
	
	@Override
	public void removeSlot(int availableSlot)
	{
		freeSlots.remove(availableSlot);
	}
}
