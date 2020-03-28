/**
 * 
 */
package io.gojek.parkinglot.processor.data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.gojek.parkinglot.exception.ParkingLotException;

/**
 * @author vaibhav.singh
 *
 */
public class ParkingActionTest
{
	@Test
	public void testValidParkingActions() throws ParkingLotException
	{
		assertTrue(ParkingAction.getAction("create_parking_lot") == ParkingAction.CREATE_PARKING_LOT);
		assertTrue(ParkingAction.getAction("park") == ParkingAction.PARK);
		assertTrue(ParkingAction.getAction("leave") == ParkingAction.LEAVE);
		assertTrue(ParkingAction.getAction("status") == ParkingAction.STATUS);
		assertTrue(ParkingAction.getAction("registration_numbers_for_cars_with_colour") == ParkingAction.REG_NUMBER_FOR_CARS_WITH_COLOR);
		assertTrue(ParkingAction.getAction("slot_numbers_for_cars_with_colour") == ParkingAction.SLOTS_NUMBER_FOR_CARS_WITH_COLOR);
		assertTrue(ParkingAction.getAction("slot_number_for_registration_number") == ParkingAction.SLOTS_NUMBER_FOR_REG_NUMBER);
	}

	@Test(expected = ParkingLotException.class)
	public void testInValidParkingActions() throws ParkingLotException
	{
		assertNotNull(ParkingAction.getAction("invalid_action"));
	}

	@Test(expected = ParkingLotException.class)
	public void testInValidBlankParkingActions() throws ParkingLotException
	{
		assertNotNull(ParkingAction.getAction(" "));
	}

	@Test(expected = ParkingLotException.class)
	public void testInValidNullParkingActions() throws ParkingLotException
	{
		assertNotNull(ParkingAction.getAction(null));
	}

	@Test
	public void testValidParkingArgs() throws ParkingLotException
	{
		assertTrue(ParkingAction.getActionArgs("create_parking_lot") == 1);
		assertTrue(ParkingAction.getActionArgs("park") == 2);
		assertTrue(ParkingAction.getActionArgs("leave") == 1);
		assertTrue(ParkingAction.getActionArgs("status") == 0);
		assertTrue(ParkingAction.getActionArgs("registration_numbers_for_cars_with_colour") == 1);
		assertTrue(ParkingAction.getActionArgs("slot_numbers_for_cars_with_colour") == 1);
		assertTrue(ParkingAction.getActionArgs("slot_number_for_registration_number") == 1);
	}

	@Test(expected = ParkingLotException.class)
	public void testInValidParkingActionsArgs() throws ParkingLotException
	{
		assertNotNull(ParkingAction.getActionArgs("invalid_action"));
	}

	@Test(expected = ParkingLotException.class)
	public void testInValidBlankParkingActionArgs() throws ParkingLotException
	{
		assertNotNull(ParkingAction.getActionArgs(" "));
	}

	@Test(expected = ParkingLotException.class)
	public void testInValidNullParkingActionArgs() throws ParkingLotException
	{
		assertNotNull(ParkingAction.getActionArgs(null));
	}
}
