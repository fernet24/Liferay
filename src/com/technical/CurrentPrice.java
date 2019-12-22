package com.technical;

/**
 * The CurrentPrice class tracks the sales tax and total
 */

import java.util.ArrayList;


public class CurrentPrice 
{
	private double currentSalesTax;
	private double currentTotal;
	
	public CurrentPrice()
	{
		currentSalesTax = 0;
		currentTotal = 0;
	}
	
	public CurrentPrice(double currentSalesTax, double currentTotal)
	{
		this.currentSalesTax = currentSalesTax;
		this.currentTotal = currentTotal;
	}

	public double getCurrentSalesTax() {
		return currentSalesTax;
	}

	public void setCurrentSalesTax(double currentSalesTax) {
		this.currentSalesTax = currentSalesTax;
	}

	public double getCurrentTotal() {
		return currentTotal;
	}

	public void setCurrentTotal(double currentTotal) {
		this.currentTotal = currentTotal;
	}
	/**
	 *
	 * @param beforeTax: Stores given values into a list and prints them in the method
	 * @param afterTax:  Stores modified values into a list and prints them in the method
	 * @param taxObject: Tax object prints the finalize sales tax value and total amount
	 */
	public static void print(ArrayList<String> beforeTax, ArrayList<Double> afterTax, Tax taxObject)
	{
		System.out.println("Before tax: " + beforeTax.toString());
        System.out.println("After tax: " + afterTax.toString());
        System.out.println("Sales Tax: " + Round.nearestOnes(taxObject.getSalesTax()));
        System.out.println("Total: " + Round.nearestOnes(taxObject.getTotal()));
	}
}
