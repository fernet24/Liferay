package com.technical;

/**
 * The Round class contains two methods which round 
 * to the nearest ones & nearest .05
 *
 */

public class Round
{
	/**
	 * @param value: The number is rounded to the nearest ones place
	 * @return The value is returned rounded
	 */
	public static double nearestOnes(double value)
	{
        return Math.round(value * 100.0) / 100.0;
	}
	
	/**
	 * @param value: The number is rounded to the nearest .05 place
	 * @return The number is truncated is returned as the following format: 0.00
	 */
	public static double nearestFifth(double value)
	{
		if(value > .54 && value < 1.00)
		{
			value = value * 100;
	        value = Math.round(value *.05);
	        value = value / .05;
	        value = value / 100;
	        return value;
		}
		return(int)(value * 20.0 + 0.5) / 20.0;
	}
}
