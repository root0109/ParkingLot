/**
 * 
 */
package io.gojek.parkinglot.processor;

import io.gojek.parkinglot.exception.ParkingLotException;
import io.gojek.parkinglot.processor.data.Request;
import io.gojek.parkinglot.service.AbstractService;

/**
 * @author vaibhav.singh
 *
 */
public interface AbstractProcessor
{
	boolean validateInput(Request request);

	boolean execute(Request request) throws ParkingLotException;

	void setService(AbstractService service);
}
