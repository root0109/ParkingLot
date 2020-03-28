/**
 * 
 */
package io.gojek.parkinglot.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author vaibhav.singh
 *
 */
public class InputValidationUtilTest
{
	@Test
	public void testValidInputStringWithNoInputParam()
	{
		assertTrue(InputValidationUtil.validate("status"));
	}

	@Test
	public void testValidInputStringWithOneInputParam()
	{
		assertNotNull(InputValidationUtil.validate("create_parking_lot 6"));
	}

	@Test
	public void testValidInputStringWithTwoInputParam()
	{
		assertTrue(InputValidationUtil.validate("park KA-01-P-333 White"));
	}

	@Test
	public void testInValidInputString()
	{
		assertFalse(InputValidationUtil.validate("invalid string"));
	}

	@Test
	public void testInValidNullInputString()
	{
		assertFalse(InputValidationUtil.validate(null));
	}

	@Test
	public void testInValidBlankInputString()
	{
		assertFalse(InputValidationUtil.validate(""));
	}
}
