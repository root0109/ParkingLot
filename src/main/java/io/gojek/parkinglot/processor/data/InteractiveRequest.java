/**
 * 
 */
package io.gojek.parkinglot.processor.data;

/**
 * @author vaibhav.singh
 *
 */
public class InteractiveRequest implements Request
{
	private String inputString = null;

	public InteractiveRequest(String inputString)
	{
		this.inputString = inputString;
	}

	/**
	 * @return the inputString
	 */
	public String getInputString()
	{
		return inputString != null ? inputString.trim() : null;
	}

	/**
	 * @param inputString the inputString to set
	 */
	public void setInputString(String inputString)
	{
		this.inputString = inputString;
	}
}
