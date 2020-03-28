/**
 * 
 */
package io.gojek.parkinglot.model;

/**
 * @author 91845
 *
 */
public class ParkingLot
{
	private String parkingId;
	private String address;
	private int    levels;
	private int    slots;

	/**
	 * @return the parkingId
	 */
	public String getParkingId()
	{
		return parkingId;
	}

	/**
	 * @param parkingId the parkingId to set
	 */
	public void setParkingId(String parkingId)
	{
		this.parkingId = parkingId;
	}

	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * @return the levels
	 */
	public int getLevels()
	{
		return levels;
	}

	/**
	 * @param levels the levels to set
	 */
	public void setLevels(int levels)
	{
		this.levels = levels;
	}

	/**
	 * @return the slots
	 */
	public int getSlots()
	{
		return slots;
	}

	/**
	 * @param slots the slots to set
	 */
	public void setSlots(int slots)
	{
		this.slots = slots;
	}
}
