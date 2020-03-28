/**
 * 
 */
package io.gojek.parkinglot.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import io.gojek.parkinglot.exception.ParkingLotException;

/**
 * @author vaibhav.singh
 *
 */
public class ParkingUtilTest
{
	@Test
	public void testGetInteractiveRequest()
	{
		assertNotNull("Object created with null string.", ParkingUtil.getInteractiveRequest(null));
		assertNotNull("Object created with an input value", ParkingUtil.getInteractiveRequest("create_parking_lot 6"));
	}

	@Test
	public void testGetParkingActionNotNull() throws ParkingLotException
	{
		assertNotNull(ParkingUtil.getParkingAction("create_parking_lot"));
	}

	@Test(expected = ParkingLotException.class)
	public void testGetParkingActionNull() throws ParkingLotException
	{
		assertNotNull(ParkingUtil.getParkingAction(null));
	}

	@Test(expected = ParkingLotException.class)
	public void testGetParkingActionIsBlank() throws ParkingLotException
	{
		assertNotNull(ParkingUtil.getParkingAction(""));
	}
}
