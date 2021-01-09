/*****************************************
** File:    MerkleTree.java
** Project: CSCE 314 Final Project, Fall 2020
** Author:  Xingjian Hao, Maxwell Griffith
** Date:    11/22/2020
** Section: 501
** E-mail:  haoxj0122@tamu.edu
**			maxcollegelife@tamu.edu 
**
**   This file contains the Merkle Tree data structure. It contains 
** a root hash and a list to store all
** the data that is going to store in the Tree.	
** The intermittent nonLeaves and the Root itself are still in progress,
** but there is still validation for the hash root
***********************************************/
import java.util.List;
import java.lang.Math.*;
import java.util.ArrayList;

public class MerkleTree 
{
	private String rootHash;
	public List<Leaf> leaves;
	private List<nonLeaf> nonLeaves;
	
	public MerkleTree()
	{
		leaves = new ArrayList<Leaf>();
		nonLeaves = new ArrayList<nonLeaf>();
		//Nodes.add()
	}
	
	//-------------------------------------------------------
	// Name: MerkleTree
	// PreCondition:  the data signature is stored in list
	// PostCondition: Returns the root of created Merkle Tree
	//---------------------------------------------------------
	public MerkleTree(List<String> dataSig)
	{
		leaves = new ArrayList<Leaf>();
		nonLeaves = new ArrayList<nonLeaf>();
		//System.out.println(dataSig.get(0));
		//creates the Leaves and the parent (nonLeaves) for them
		for (int i = 0; i < dataSig.size(); i++)
		{
			// Generate an array of all the child nodes
			String sig = dataSig.get(i);
			nonLeaf newNon = new nonLeaf(sig);
			nonLeaves.add(newNon);
			Leaf newLeaf = new Leaf(newNon,sig);
			leaves.add(newLeaf);
			
		}
		//System.out.println(dataSig.get(0));
		cons(nonLeaves);
		
			
	}
	public nonLeaf cons(List<nonLeaf> dataSig)
	{
		
		//dataSig = new List<nonLeaf>(dataSig);
		
				// A list to hold the parents
				// It will be used to hold values that will be used to ad more parents for the next level
				List<nonLeaf> sigList = new ArrayList<>();
				
				//if(data.size)
				// The for loop takes 2 elements from the list each iteration
				if(dataSig.size() == 1)
				{
					rootHash = dataSig.get(0).getHashValue();
					return dataSig.get(0);
				}
				
				//System.out.println(dataSig.size());
				for (int i = 0; i < dataSig.size() - 1; i += 2)
				{
					
	
					// Merge the hash values of two children and create a new parent node
					String mergedSig = merge(dataSig.get(i).getHashValue(), dataSig.get(i+1).getHashValue());
					//System.out.println(mergedSig);
					nonLeaf newParent = new nonLeaf(mergedSig);
					dataSig.get(i).setParent(newParent);
					dataSig.get(i+1).setParent(newParent);
					
					
					// store the hash value into a new list for the next recursion
					sigList.add(newParent);
					

				}
				
				// if the length of dataSig is an odd number
				if (dataSig.size() % 2 == 1)
				{
					String soleMerge = merge(dataSig.get(dataSig.size() - 1).getHashValue(), 
							dataSig.get(dataSig.size() - 1).getHashValue());
					nonLeaf newParent = new nonLeaf(soleMerge);
					dataSig.get(dataSig.size()-1).setParent(newParent);
					sigList.add(newParent);
				}
				
				// recursion with the updated list
				return cons(sigList);
		
	}
	
	public void add(String message)
	{
			/*Leaf newLeaf; 
			nonLeaf newNon;
			//creates a non leaf and a leaf since a leaf needs a nonleaf to be the hash
			newNon = new nonLeaf(message);
			newLeaf = new Leaf(newNon, message);
			leaves.add(newLeaf);
			Hash newHash = new Hash();
			if(leaves.size() == 1)
				return;
			//for size 2, create a new node and merge it with first for root
			if(leaves.size() == 2)
			{
				
				String newHashValue = merge(leaves.get(0).getParent().getHashValue(), leaves.get(1).getParent().getHashValue());
				newHashValue = newHash.getHash(message);
				newNon.setParent(new nonLeaf(newHashValue));
				leaves.get(0).getParent().setParent(new nonLeaf(newHashValue));
				return;
				//done
			}
			//should be every node after, need to determine how to get the hash function and then hash all the way up
			else
			{
				//int neededNewNodes;
				if(leaves.size() % 2 == 0)
				{
					String newHashValue = merge(leaves.get(leaves.size()-1).getParent().getHashValue(), leaves.get(leaves.size()-2).getParent().getHashValue());
					newNon.setParent(leaves.get(leaves.size()-2).getParent());
					//need to add recursive re-hashing
					
				}
				else //this means that this leaf will be odd, which means we will have to create nodes for the parent node that we created
				{
					
					//need to add creating the correct number of nodes and then re-hashing down the entire tree
					
				}
				//neededNewNodes = 0;
				//System.out.println(neededNewNodes);
				
				
				
				
			}
		
				/*int neededDepth = (int)Math.ceil(Math.log(leaves.size())/Math.log(2.0));
			
				if(leaves.size() % 2 == 1)
				{
					String newHashValue = newNon.getCurrentHash();
					newNon.setParent(new nonLeaf(message));
					
					
				}
				
			
				String newHashValue = merge(leaves.get(0).getParent().getHashValue(), leaves.get(1).getParent().getHashValue());
				Hash newHash = new Hash();
				newHashValue = newHash.getHash(message);
				System.out.println(newHashValue);
				
				
				//if(leaves.size() %2 == 1)
				 * */
				 
		
			

	}
	
	public void delete (String Node)
	{
	
			for(int x = 0; x < leaves.size(); x++)
			{
				if(leaves.get(x).getMessage().compareTo(Node) == 0)
				{
					leaves.remove(x);
					System.out.println("Item has been removed at location " + x);
					return;
				}
			}
		System.out.println("Value is not containe in tree");
		
		//need to work on re-hashing down the whole tree
	}
	//-------------------------------------------------------
	// Name: constructLeaves
	// PreCondition:  the data signature is stored in list and the amount of data is at least one
	// PostCondition: Returns a list of the leaves that are input from the data signature
	// This is a function that creates the leaves and the corresponding hash values
	//---------------------------------------------------------
	
	
		/*public List<Leaf> constructLeaves(List<String> signature)
		{
			if(signature.size()== 1)
			{	
				leaves.add(new Leaf(signature.get(0),true,true));
			}
			else
			{
				// The for loop takes 2 elements from the list each iteration if possible
				for (int i = 0; i < signature.size(); i += 2)
				{
					
					// Generate two child nodes with the values if there are two to generate
					String leftSig = signature.get(i);
					String rightSig = "";
					Leaf newLeft = new Leaf(leftSig, true, false);
					leaves.add(newLeft);
					
					//seeing if we will have a right element to be hashed
					//since we are already guaranteed to have a left ones
					if(signature.size() - i > 1)
					{
						rightSig = signature.get(i + 1);
						Leaf newRight = new Leaf(rightSig, false, true);
						leaves.add(newRight);
					}
						
				}
			}
			return leaves;
		}*/
		
		
		
		//-------------------------------------------------------
		// Name: createRootHash
		// PreCondition:  the leaves that were created and stores in the leaves ArrayList
		// PostCondition: Returns the root hash value
		// This is a function that finds the root hash value through merging the other hash values together
		//---------------------------------------------------------
		/*public String createRootHash(List<Leaf> leaves)
		{
			List<Leaf> newNodes = new ArrayList<Leaf>();
			if(leaves.size() == 1)
				return "" + leaves.get(0).getCurrentHash();
			
			for(int x = 0; x < leaves.size(); x+=2)
			{
				// Generate two child nodes with the values
				String leftHash = "" + leaves.get(x).getCurrentHash();
				String rightHash = "";
				
				//seeing if we will have a right element to be hashed
				if(leaves.size() - x > 1)
				{
					rightHash= leaves.get(x + 1).getCurrentHash();
					//System.out.println(rightSig);
				}
				
				//Merge the two hashes together
				String mergedHash = merge(leftHash, rightHash);
				//Create a new parent with a hash of the two concatenated hash values
				Leaf newParent = new Leaf(mergedHash);
				newNodes.add(newParent);
			}
			return "" + createRootHash(newNodes);
			
		}*/
		
	public int numElements()
	{
		return leaves.size();
	}
	//-------------------------------------------------------
	// Name: getRootHash
	// PreCondition:  Null
	// PostCondition: Returns the hash value of the Root 
	//---------------------------------------------------------
	public String getRootHash()
	{ 
		if(leaves.size() != 0)
		{
			nonLeaf nonL = leaves.get(0).getParent();
			while(nonL.getParent() != null)
			{
				nonL = nonL.getParent();
			}
			return nonL.getHashValue();
		}
		else return "Merkle Tree is empty!";
				
	};
	
	//-------------------------------------------------------
	// Name: merge
	// PreCondition: two pieces of data signature
	// PostCondition: returns the merged string for encoding in parent node
	//---------------------------------------------------------
	public String merge(String leftPiece, String rightPiece)
	{
		// merge the data by conjunct the strings
		String mergedString = leftPiece + rightPiece;
		return mergedString;
	}

	//-------------------------------------------------------
	// Name: toString
	// PreCondition: null
	// PostCondition: prints the message and hash values of the input leaves
	//---------------------------------------------------------
	public String toString()
	{
		for(int x = 0; x < leaves.size(); x++)
		{
			System.out.println(leaves.get(x) + "   " + leaves.get(x).parent);
		}
		return "";
	}
	
}