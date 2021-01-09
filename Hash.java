
/*****************************************
** File:    Hash.java
** Project: CSCE 314 Final Project, Fall 2020
** Author:  Xingjian Hao, Maxwell Griffith
** Date:    11/22/2020
** Section: 501
** E-mail:  haoxj0122@tamu.edu
**			maxcollegelife@tamu.edu 
**
**   This file contains the implementation of hash data structure.
** The hash is basically a string of encoded message. SHA-256 standard
** is used to generate the hash value in this project. It's also used
** in the transaction of bitcoins to ensure the security.
***********************************************/
import java.security.MessageDigest; 

public class Hash {
	
	protected String hashValue;
	
	//-------------------------------------------------------
	// Name: getHash
	// PreCondition: the data signature to be encoded
	// PostCondition: returns the hexadecimal message encode with SHA-256 method.
	//---------------------------------------------------------
	public static String getHash(String dataSig)
	{
		try
		{
			MessageDigest valDigest = MessageDigest.getInstance("SHA-256");
			
			// divide the data by byte
			byte[] hash = valDigest.digest(dataSig.getBytes("UTF-8"));
			
			// String to store the outcome
			StringBuffer hexaString = new StringBuffer();
			
			for (int i = 0; i < hash.length; i++)
			{
				// For each byte of the data, turn it into a 2 bits long hexadecimal number
				String hexaVal = Integer.toHexString(0xff & hash[i]);
				
				// place a 0 if the hexadecimal format only have 1 bits
				if (hexaVal.length() == 1)
				{ hexaString.append("0"); }
				hexaString.append(hexaVal);
			}
			//System.out.println(hexaString.toString());
			return hexaString.toString();
		}
		catch (Exception exp)
		{
			throw new RuntimeException(exp);
		}		
	}
}