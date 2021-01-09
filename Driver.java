/*****************************************
** File:    Driver.java
** Project: CSCE 314 Final Project, Fall 2020
** Author:  Xingjian Hao, Maxwell Griffith
** Date:    11/7/2020
** Section: 501
** E-mail:  haoxj0122@tamu.edu
**			maxcollegelife@tamu.edu 
**
**   This file is the driver of the project. In this project, we'll use 
** Merkle tree to encode a piece of important data. The system will read
** the instruction and do encoding/decoding respectively. To encode the
** data, the input file have to contains one true message and some fake
** messages mixed together. Each message will be placed in the single line.
** To encode the message, the system will read the input file by lines, and 
** build up a Merklke tree based on these messages. After the system
** build the tree, it will generate a password file with the root hash and the 
** hash values of corresponding nodes to locate the message. Then the user can
** send the password file to the receiver. The receiver will the read the password
** file with the system and indicate that the file is going to be decoded. Then
** the system will rebuild the merkle tree with the hash values provided and the
** hash value of the real message can be founded. Then just decode this message
** to get the real content.
***********************************************/
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Driver {
	
	public static ArrayList<ArrayList<String>> permutation(ArrayList<String> inputList)
	{
		// if the list is empty
		if (inputList.size() == 0)
		{
			ArrayList<ArrayList<String>> empty = new ArrayList<ArrayList<String>>();
			ArrayList<String> emptyElement = new ArrayList<String>();
			emptyElement.add("");
			empty.add(emptyElement);
			return empty;
		}
		
		// take the first message from input list
		ArrayList<String> firstMsg = new ArrayList<String>();
		firstMsg.add(inputList.get(0));
		
		// Get the sublist of input list from the second element to the last one
		int listSize = inputList.size() - 1;
		ArrayList<String> subArrayList = new ArrayList<String>(inputList.subList(1, listSize));
		
		ArrayList<ArrayList<String>> preResult = permutation(subArrayList);
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		for (ArrayList<String> probability : preResult)
		{
			for (int i = 0; i <= probability.size(); i++)
			{
				ArrayList<String> probList = new ArrayList<String>(probability.subList(0, i));
				probList.addAll(firstMsg);
				if (probability.size() == 1)
				{
					probList.addAll(probability);
				}
				else
				{
					probList.addAll(probability.subList(i, probability.size() - 1));
				}
				result.add(probList);
			}
		}
		
		return result;
	}
		
	public static void main(String args[])
	{
		
		// Decide if encoding or decoding
		//System.out.print("Do you want to encode or decode? (EN / DE):");
		Scanner inputCho = new Scanner(System.in);
		String choice = JOptionPane.showInputDialog(null, "Do you want to encode or decode? (EN / DE):");
		
		if (choice.equals("EN"))		// Encoding
		{			
			String fileName;
			String dir = System.getProperty("user.dir");
			//System.out.println("Please type the name of the file you want to encode (Message.txt by default):");
				
			fileName = JOptionPane.showInputDialog(null, 
					"Please type the name of the file you want to encode (Message.txt by default):");
			String fileLoc = dir + "\\src\\" + fileName;
			// Testify if the file exist 
			Scanner inFile = null;
			try
			{
				inFile = new Scanner(new FileReader(fileLoc));
			} 
			catch (FileNotFoundException e) 
			{
				//System.out.println("File not found");
				JOptionPane.showMessageDialog (null, "Error: File not found ", 
						"Merkle Tree Encryption System", JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace(); 
				System.exit(0);
			}
			
			// Create a new list to read data
			List<String> readinList = new ArrayList<String>();

			while (inFile.hasNextLine())
			{
				// Every line in the file will be an element of the list
				String message = inFile.nextLine();
				readinList.add(message);
			}
					
			// Shuffle the message list to encode
			Collections.shuffle(readinList);
			MerkleTree encoded2 = new MerkleTree(readinList);
			String rootHash2 = "" + encoded2.getRootHash();
						
			// Generate the output file, which is the disordered messages with a root hash
			String outDir = System.getProperty("user.dir");
			String outLoc = outDir + "\\src\\" + "Encoded.txt";
			
			PrintWriter outFile = null;
			try
			{
				outFile = new PrintWriter(outLoc);
			}
			catch (FileNotFoundException e)
			{
				//System.out.println("File not found");
				JOptionPane.showMessageDialog (null, "Error: File not found ", 
						"Merkle Tree Encryption System", JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
				System.exit(0);
			}
			
			outFile.println(rootHash2);
			
			for (int i = 0; i < readinList.size(); i++)
			{
				outFile.println(readinList.get(i));
			}
			
			//System.out.println("Encode finished. The file Encoded.txt can be found in the local src directory. ");
			JOptionPane.showMessageDialog (null, "Encode finished. The file Encoded.txt can be found in the local src directory.  ", 
					"Merkle Tree Encryption System", JOptionPane.INFORMATION_MESSAGE);
			inFile.close();
			outFile.close();
			
		}
		
		else if (choice.equals("DE"))		// Decoding
		{
			System.out.println("Decoding...");
			
			//1, read in the file
			String fileName;
			String dir = System.getProperty("user.dir");
			System.out.println("Please type the name of the file you want to decode (Encoded.txt by default):");
				
			fileName = inputCho.next();
			String fileLoc = dir + "\\src\\" + fileName;
			System.out.println("Read in location: " + fileLoc);
			// Testify if the file exist 
			Scanner inFile = null;
			try
			{
				inFile = new Scanner(new FileReader(fileLoc));
			} 
			catch (FileNotFoundException e) 
			{
				JOptionPane.showMessageDialog (null, "Error: File not found ", 
						"Merkle Tree Encryption System", JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace(); 
				System.exit(0);
			}
			
			// the file comes with root hash + disordered messages
			// 2. count on the total number of messages and build a merkle tree for each possibility
			ArrayList<String> readinList = new ArrayList<String>();
			String trueHash = inFile.nextLine();
			int numMsg = 0;
			
			while (inFile.hasNextLine())
			{
				// Every line in the file will be an element of the list
				String message = inFile.nextLine();
				System.out.println(message);
				readinList.add(message);
				numMsg += 1;
			}
			
			System.out.println("The true hash = " + trueHash);
			System.out.println("There are " + numMsg + " messages.");
			// 3. also record the order in each run
			// 4. compare those values with the correct roothash
			// 5. if match, printout the message in correct order
			
			ArrayList<ArrayList<String>> storedPossibilities = permutation(readinList);
			ArrayList<String> decoded = new ArrayList<String>();
			
			for (int i = 0; i < storedPossibilities.size(); i++)
			{
				MerkleTree currPoss = new MerkleTree(storedPossibilities.get(i));
				String currentHash = currPoss.getRootHash();
				if (currentHash == trueHash)
				{
					decoded.addAll(storedPossibilities.get(i));
					break;
				}
			}
			
			// Print out the result
			for (String msg : decoded)
			{
				System.out.println(msg);
			}
			
		}
		
		
		else 
		{ 
			JOptionPane.showMessageDialog (null, "Error: not a feasible choice ", 
				"Merkle Tree Encryption System", JOptionPane.INFORMATION_MESSAGE); 
		}

	}
}
