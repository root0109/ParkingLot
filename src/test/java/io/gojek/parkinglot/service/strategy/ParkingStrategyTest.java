/**
 * 
 */
package io.gojek.parkinglot.service.strategy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author vaibhav.singh
 *
 */
public class ParkingStrategyTest
{
	private static ParkingStrategy parkingStrategy = null;

	@Before
	public void init()
	{
		parkingStrategy = new NearestFirstStrategy();
	}

	/*
	 * -ve Test case of Invalid File
	 */
	@Test
	public void testAddNearestFirstStrategy()
	{
		assertTrue(parkingStrategy.add(1));
		assertTrue(parkingStrategy.add(2));
		assertTrue(parkingStrategy.add(3));
	}

	@Test
	public void testRemoveNearestFirstStrategy()
	{
		assertTrue(parkingStrategy.add(1));
		assertTrue(parkingStrategy.add(2));
		assertTrue(parkingStrategy.add(3));
		assertTrue(parkingStrategy.removeSlot(2));
		assertTrue(parkingStrategy.removeSlot(1));
		assertTrue(parkingStrategy.getSlot() == 3);
	}

	@Test
	public void testRemoveNearestFirstStrategyWhichisNotFree()
	{
		assertTrue(parkingStrategy.add(1));
		assertTrue(parkingStrategy.add(2));
		assertTrue(parkingStrategy.add(3));
		assertFalse(parkingStrategy.removeSlot(4));
	}

	@Test
	public void testGetSlot()
	{
		assertTrue(parkingStrategy.add(1));
		assertTrue(parkingStrategy.add(2));
		assertTrue(parkingStrategy.getSlot() == 1);
	}
}
