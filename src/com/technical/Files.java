package com.technical;

/**
 * The Files class contains two methods which replaces words within 
 * the text file & copies content from another text file
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Files
{
	/**
	 * 
	 * @param filePath:	The given file is read and modified
	 * @param oldWord:  Selects a word that is going to be replaced
	 * @param newWord:  The word replaces the old word
	 */
	public static void updateFile(String filePath, String oldWord, String newWord)
	{
		File newFile = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(newFile));
             
            String line = reader.readLine();
             
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
             
            String newContent = oldContent.replaceAll(oldWord, newWord);
             
            writer = new FileWriter(newFile);
             
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
	}
	/**
	 * @param ins: The input file is read and copied to the new file
	 * @param outs: The file receives the content from the given text file
	 */
	public static void fileCopy(FileInputStream ins, FileOutputStream outs)
	{
		try {
		byte[] buffer = new byte[1024];
	    int length;
	         
	    while ((length = ins.read(buffer)) > 0) 
	    {
	        outs.write(buffer, 0, length);
	    } 
	    System.out.println("File copied successfully!!");
	}catch(IOException ae)
		{
			ae.printStackTrace();
		}
	}
	
}
