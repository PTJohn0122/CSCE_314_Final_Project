/*****************************************
** File:    nonLeaf.java
** Project: CSCE 314 Final Project, Fall 2020
** Author:  Xingjian Hao, Maxwell Griffith
** Date:    12/08/2020
** Section: 501
** E-mail:  haoxj0122@tamu.edu
**			maxcollegelife@tamu.edu 
**
**   This file contains the nonLeaf class. It contains 
**   all the nodes that are either the root or anything else except the leaves.
**   this class is separate from the Leaf class because there is no message to be seen.
***********************************************/
public class nonLeaf extends Node
{
	//-------------------------------------------------------
	// Name: nonLeaf
	// PreCondition: null
	// PostCondition: returns a node with no hash value and no children connected
	//---------------------------------------------------------
	private String hashValue;
	
	//-------------------------------------------------------
	// Name: nonLeaf
	// PreCondition: null
	// PostCondition: calls the superclass node
	//---------------------------------------------------------
	public nonLeaf()
	{
		super();
	}
	
	//-------------------------------------------------------
	// Name: nonLeaf
	// PreCondition: null
	// PostCondition: constructor, will be used in the future when non leafs are created for the tree
	//---------------------------------------------------------
	public nonLeaf(String hash)
	{	
		Hash newHash = new Hash();
		this.hashValue = newHash.getHash(hash);
		//System.out.println(hashValue);
		
	}
	public String getHashValue()
	{
		return this.hashValue;
	}
	
	//-------------------------------------------------------
	// Name: toString()
	// PreCondition: null
	// PostCondition: returns a node with no hash value and no children connected
	//---------------------------------------------------------
	public String toString()
	{
		return "Hash Value: " + this.hashValue;
	}
	

}