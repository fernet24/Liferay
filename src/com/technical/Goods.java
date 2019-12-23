package com.technical;

/**
 * The LiferayChallenge program receives an input text file which calculates the total sales tax and the total amount (excluding
 * the sales tax). Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that are 
 * exempt. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions. After the
 * charges have been applied, the information is printed to a new text file.
 * 
 * @author: Eric Ramirez
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Scanner;

public class Goods 
{
	public static void main(String[] args)
	{
		try {
			Tax taxObject = new Tax();
			CurrentPrice update = new CurrentPrice();
			ArrayList<String> beforeTax = new ArrayList<String>();
	        ArrayList<Double> afterTax = new ArrayList<Double>();
	        
	        System.out.println("Please insert text file: ");
	        
	        Scanner in = new Scanner(System.in);
	        String file = in.nextLine();
			
			File input1 = new File(file);
			
			FileReader reader = new FileReader(input1); 
	        BufferedReader bufferRead = new BufferedReader(reader); 
	        StreamTokenizer token = new StreamTokenizer(bufferRead); 
	       
	        int currentToken; 
	        
	        //Reads text file and determines if the content in the text file is either a word or number
	        while ((currentToken = token.nextToken()) != StreamTokenizer.TT_EOF) 
	        { 
	        	 if(currentToken == StreamTokenizer.TT_WORD)
	             {
	             	if(token.sval.equals("imported"))
	             		taxObject.setImportFlag(true);
	             	if(token.sval.equals("book") || token.sval.equals("bar") || token.sval.equals("pills") || token.sval.equals("box"))
	             		taxObject.setExemptFlag(true);
	             }
	        	 
	            if(currentToken == StreamTokenizer.TT_NUMBER)
	            {
	            	if(token.nval != 1.0)
	            	{
	            		//stores default values
	            		beforeTax.add(String.valueOf(token.nval)); 
	            		
		            	if(taxObject.isImportFlag() == true && taxObject.isExemptFlag() == true)
		            	{
		            		//stores sales tax
		            		taxObject.setSalesTax(taxObject.getSalesTax() + Round.nearestFifth(token.nval * Tax.importedTax));
		            		
		            		//tracks total amount
		            		update.setCurrentTotal(token.nval + Round.nearestFifth(token.nval * Tax.importedTax));
		            		
		            		//stores total amount
		            		taxObject.setTotal(taxObject.getTotal() + update.getCurrentTotal());
		            		
		            		//stores values after calculated
		            		afterTax.add(Round.nearestOnes(update.getCurrentTotal()));
		            		
		            		taxObject.setImportFlag(false);
		            		taxObject.setExemptFlag(false);
		            	}
		            	else if(taxObject.isImportFlag() == true && taxObject.isExemptFlag() == false)
		            	{
		            		//tracks current sales tax
		            		update.setCurrentSalesTax(Round.nearestFifth(token.nval * Tax.importedTax) + Round.nearestFifth(token.nval * Tax.basicTax));
		            		
		            		//stores sales tax
		            		taxObject.setSalesTax(taxObject.getSalesTax() + update.getCurrentSalesTax());
		            		
		            		//tracks total amount
		            		update.setCurrentTotal(token.nval + update.getCurrentSalesTax());
		            		
		            		//stores total amount
		            		taxObject.setTotal(taxObject.getTotal() + update.getCurrentTotal());
		            		
		            		//stores value after calculated
		            		afterTax.add(Round.nearestOnes(update.getCurrentTotal())); 
		            		
		            		taxObject.setImportFlag(false);
		            	}
		            	else if(taxObject.isImportFlag() == false && taxObject.isExemptFlag() == true)
		            	{
		            		//stores total amount
		            		taxObject.setTotal(taxObject.getTotal() + token.nval);
		            		
		            		//stores value after calculated
		            		afterTax.add(Round.nearestOnes(token.nval));
		            		
		            		taxObject.setExemptFlag(false);
		            	}
		            	else  
		            	{
		            		//tracks sales tax
		            		update.setCurrentSalesTax(Round.nearestFifth(token.nval * Tax.basicTax));
		            		
		            		//stores sales tax amount
		            		taxObject.setSalesTax(taxObject.getSalesTax() + token.nval * Tax.basicTax);
		            		
		            		//tracks total amount
		            		update.setCurrentTotal(token.nval + update.getCurrentSalesTax());
		            		
		            		//stores total amount
		            		taxObject.setTotal(taxObject.getTotal() + update.getCurrentTotal());
		            		
		            		afterTax.add(Round.nearestOnes(update.getCurrentTotal()));
		            	}
	            	}
	            }
	          
	        } 
	        //prints information to console
	        CurrentPrice.print(beforeTax, afterTax, taxObject);
	        
	        System.out.println("");
	        System.out.println("Please enter output text file: ");
	        
	        String output = in.nextLine();
	        
	        //copies content of input file     
	        FileInputStream ins = null;
		    FileOutputStream outs = null;
		      
		    File outFile = new File(output);
		    ins = new FileInputStream(input1);
		         
		    outs = new FileOutputStream(outFile);
		    
		    Files.fileCopy(ins, outs);
		         
		    //replaces a word within the text file     
		    Files.updateFile(output, " at ", ": ");
		         
		    //replaces a number within the text file
		    for(int i = 0; i <= beforeTax.size() - 1; i++)
		        Files.updateFile(output, beforeTax.get(i), String.valueOf(afterTax.get(i)));
		         
		    //Writes to the output file
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));
		    out.println("Sales Tax: " + Round.nearestOnes(taxObject.getSalesTax()));
		    out.println("Total: " + Round.nearestOnes(taxObject.getTotal()));
		         
		    out.close();
		    ins.close();
		    outs.close();
	        reader.close();
	        
		}catch(IOException ae)
		{
			ae.printStackTrace();
		}
		
		
	}

}
