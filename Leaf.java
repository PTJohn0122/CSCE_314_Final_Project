/*****************************************
** File:    Leaf.java
** Project: CSCE 314 Final Project, Fall 2020
** Author:  Xingjian Hao, Maxwell Griffith
** Date:    11/22/2020
** Section: 501
** E-mail:  haoxj0122@tamu.edu
**			maxcollegelife@tamu.edu 
**
**   This file contains the Leaf class. It contains 
**   all the leaf nodes, the nodes with the messages.
**   this class is separate from the nonLeaf class because there are messages which only the leaves contain.
***********************************************/
public class Leaf extends Node
{
	private String message;
	
	//-------------------------------------------------------
	// Name: Leaf
	// PreCondition: null
	// PostCondition: creates an empty leaf
	//---------------------------------------------------------
	public Leaf()
	{
		super();
		this.message = "";
	}
	
	//-------------------------------------------------------
	// Name: Leaf
	// PreCondition: null
	// PostCondition: constructor, sets message to dataSig and left and right node. Left and right node will be null
	//---------------------------------------------------------

	
	public Leaf(nonLeaf parent, String message)
	{
		//System.out.println("Parent");
		super(parent);
		this.message = message;
	}
	//-------------------------------------------------------
	// Name: getMessage
	// PreCondition: null
	// PostCondition: returns the message
	//---------------------------------------------------------
	public String getMessage()
	{
		return this.message;
	}
	
	//-------------------------------------------------------
		// Name: toString
		// PreCondition: null
		// PostCondition: returns a node with no hash value and no children connected
		//---------------------------------------------------------
	public String toString()
	{
		return "Message: " + this.getMessage();
	}
	
	
}