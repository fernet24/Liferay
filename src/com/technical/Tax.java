package com.technical;

/**
 * The Tax class acquires the total sales tax & total amount excluding the sales tax.
 */

public class Tax 
{
	final static double importedTax = .05;
	final static double basicTax = .10;
	
	/**
	 * @member salesTax:   stores the total sales tax
	 * @member total:	   stores the overall total excluding the sales tax
	 * @member importFlag: Determines if the item is imported
	 * @member exemptFlag: Determines if the item is an exemption
	 */
	private double salesTax;
	private double total;
	private boolean importFlag;
	private boolean exemptFlag;
	
	public Tax()
	{
		salesTax = 0.0;
		total = 0.0;
		importFlag = false;
		exemptFlag = false;
	}
	public Tax(double salesTax, double total, boolean importFlag, boolean exemptFlag)
	{
		this.salesTax = salesTax;
		this.total = total;
		this.importFlag = importFlag;
		this.exemptFlag = exemptFlag;
	}
	public double getSalesTax() {
		return salesTax;
	}
	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public boolean isImportFlag() {
		return importFlag;
	}
	public void setImportFlag(boolean importFlag) {
		this.importFlag = importFlag;
	}
	public boolean isExemptFlag() {
		return exemptFlag;
	}
	public void setExemptFlag(boolean exemptFlag) {
		this.exemptFlag = exemptFlag;
	}
	public static double getImportedtax() {
		return importedTax;
	}
	public static double getBasictax() {
		return basicTax;
	}
}
