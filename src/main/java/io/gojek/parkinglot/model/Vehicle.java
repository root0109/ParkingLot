/**
 * 
 */
package io.gojek.parkinglot.model;

import java.util.Objects;

/**
 * @author vaibhav.singh
 *
 */
public abstract class Vehicle
{
	private String	licensePlateNo	= null;
	private String	color			= null;

	public Vehicle(String licensePlateNo, String color)
	{
		this.licensePlateNo = licensePlateNo;
		this.color = color;
	}

	@Override
	public boolean equals(Object object)
	{
		if (this == object)
			return true;
		if (!(object instanceof Vehicle))
			return false;
		Vehicle that = (Vehicle) object;
		return Objects.equals(getLicensePlateNo(), that.getLicensePlateNo());
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	/**
	 * @return the licensePlateNo
	 */
	public String getLicensePlateNo()
	{
		return licensePlateNo;
	}

	/**
	 * @param licensePlateNo the licensePlateNo to set
	 */
	public void setLicensePlateNo(String licensePlateNo)
	{
		this.licensePlateNo = licensePlateNo;
	}

	/**
	 * @return the color
	 */
	public String getColor()
	{
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color)
	{
		this.color = color;
	}
}
