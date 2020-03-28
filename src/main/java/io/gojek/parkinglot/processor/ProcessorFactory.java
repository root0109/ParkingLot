/**
 * 
 */
package io.gojek.parkinglot.processor;

import java.util.HashMap;
import java.util.Map;

import io.gojek.parkinglot.processor.data.ProgramRunType;

/**
 * @author vaibhav.singh
 *
 */
public final class ProcessorFactory
{
	private ProcessorFactory()
	{
		throw new IllegalArgumentException("This is not allowed");
	}

	private static final Map<ProgramRunType, AbstractProcessor> map = new HashMap<>();

	public static final AbstractProcessor createProcessor(ProgramRunType programRunType)
	{
		AbstractProcessor abstractProcessor = null;
		switch (programRunType)
		{
			default:
			case INTERACTIVE:
				abstractProcessor = map.getOrDefault(programRunType, getInteractiveProcessor());
				break;
			case FILE:
				abstractProcessor = map.getOrDefault(programRunType, getFileProcessor());
				break;
		}
		return abstractProcessor;
	}

	private static AbstractProcessor getFileProcessor()
	{
		return new FileProcessor();
	}

	private static AbstractProcessor getInteractiveProcessor()
	{
		return new InteractiveProcessor();
	}
}
