/*****************************************
** File:    Node.java
** Project: CSCE 314 Final Project, Fall 2020
** Author:  Xingjian Hao, Maxwell Griffith
** Date:    11/22/2020
** Section: 501
** E-mail:  haoxj0122@tamu.edu
**			maxcollegelife@tamu.edu 
**
**   This file contains the data structure Node, which is the
** basic component of Merkle tree. It's an inheritance form
** data structure Hash. Each node has a hash value 
** that encoded the data it want to store, and two children.
** For the nodes are on the bottom which are leaves, the hash
** value is the encoded data signature. For the nodes that are
** not leaves, their hash value stores the hash of the children.
** Idea from: https://docs.symbolplatform.com/concepts/data-validation.html
***********************************************/

public abstract class Node{
	
	protected nonLeaf parent;
	
	//-------------------------------------------------------
	// Name: Node
	// PreCondition: null
	// PostCondition: returns a node with no hash value and no children connected
	//---------------------------------------------------------
	public Node()
	{
		parent = null;
	}
	
	public Node(nonLeaf parent)
	{
		
		this.parent = parent;
	}
	//-------------------------------------------------------
	// Name: Node
	// PreCondition: a piece of data signature to store, two child nodes
	// PostCondition: returns a node with the hashed data and connected with two child nodes
	//---------------------------------------------------------

	public void setParent(nonLeaf parent)
	{
		this.parent = parent;
	}
	
	public nonLeaf getParent()
	{
		//System.out.println(this.parent);
		return this.parent;
		
	}
	//-------------------------------------------------------
	// Name: getCurrentHash
	// PreCondition: null
	// PostCondition: return the hash value of the current node
	//---------------------------------------------------------
	/*public String getCurrentHash()
	{
		return "";
		//return hashValue;
	}
	*/
	
	//-------------------------------------------------------
	// Name: updateHash
	// PreCondition: a new data signature to be stored
	// PostCondition: returns a node updated hash value
	//---------------------------------------------------------
	public void updateHash(String newDataSig)
	{
		//hashValue = getHash(newDataSig);
	}
	

	//-------------------------------------------------------
	// Name: toString
	// PreCondition: null
	// PostCondition: will go to Leaf and nonLeaf
	//---------------------------------------------------------
	public abstract String toString();
	
}