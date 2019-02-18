/**
 * 
 */
package io.gojek.parkinglot.model;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author vaibhav
 *
 */
public class Car extends Vehicle
{
	
	public Car(String registrationNo, String color)
	{
		super(registrationNo, color);
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException
	{
		super.writeExternal(out);
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
	{
		super.readExternal(in);
	}
}
