/**
 * 
 */
package io.gojek.parkinglot.service.strategy;

import java.util.TreeSet;

/**
 * @author vaibhav.singh
 *
 */
public class NearestFirstStrategy implements ParkingStrategy
{
	private TreeSet<Integer> freeSlots;

	public NearestFirstStrategy()
	{
		freeSlots = new TreeSet<>();
	}

	@Override
	public boolean add(int i)
	{
		return freeSlots.add(i);
	}

	@Override
	public int getSlot()
	{
		return freeSlots.first();
	}

	@Override
	public boolean removeSlot(int availableSlot)
	{
		return freeSlots.remove(availableSlot);
	}
}
