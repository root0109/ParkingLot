/**
 * 
 */
package io.gojek.parkinglot.processor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.gojek.parkinglot.processor.data.ProgramRunType;

/**
 * @author vaibhav.singh
 *
 */
public class ProcessorFactoryTest
{
	/*
	 * -ve Test case of Invalid File
	 */
	@Test(expected = NullPointerException.class)
	public void testProcessorFactoryThrowsNullPointerException()
	{
		assertNotNull(ProcessorFactory.createProcessor(null));
	}

	@Test
	public void testGetProcessor()
	{
		assertTrue(ProcessorFactory.createProcessor(ProgramRunType.INTERACTIVE) instanceof InteractiveProcessor);
		assertTrue(ProcessorFactory.createProcessor(ProgramRunType.INTERACTIVE) instanceof AbstractProcessor);
		assertTrue(ProcessorFactory.createProcessor(ProgramRunType.FILE) instanceof FileProcessor);
		assertTrue(ProcessorFactory.createProcessor(ProgramRunType.FILE) instanceof AbstractProcessor);
	}
}
