package BagPackage;

import java.util.Arrays;

public final class LinkedBag<T> implements BafInterface<T>
{
	private Node firstNode;		//Reference to first node
	private int numberOfEntries;
	
	public LinkedBag()
	{
		firstNode = null;
		numberOfEntries = 0;
	}
	
	private class Node //Private inner class
	{
		private T data; //Entry in bag
		private Node next; //Link to next node
		
		private Node(T dataPortion)
		{
			this(dataPortion, null)
		}
		
		private Node(T dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;
		}
		
		private T getData()
		{
			return data;
		}
		
		private void setData(T newData)
		{
			data = newData;
		}
		
		private Node getNextNode()
		{
			return next;
		}
		
		private void setNextNode(Node nextNode)
		{
			next = nextNode;
		}
	}
	
	/** Adds a new entry to this bag.
	 @param newEntry The object to be added as a new entry.
	 @return True. */
	public boolean add(T newEntry)	//OutOfMemoryError possible
	{
		//Add to beginning of chain:
		Node<T> newNode = new Node<T>(newEntry);
		newNode.setNextNode(firstNode);	//Make new node reference rest of chain
										//(firstNode is null if chain is empty)
		firstNode = newNode;			//New node is at beginning of chain
		numberOfEntries++;
		return true;
	}
	
	/** Retrieves all entries that are in this bag.
	 @return A newly allocated array of all the entries in the bag. */
	public T[] toArray()
	{
		//The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		int index = 0;
		Node currentNode = firstNode;
		
		while((index < numberOfEntries) && (currentNode != null))
		{
			result[index] = currentNode.data;
			index++;
			currentNode = currentNode.getNextNode();
		}
		
		return result;
	}
	
	/** Counts the number of times a given entry appears in this bag.
	 @param anEntry The entry to be counted.
	 @return The number of times anEntry appears in the bag. */
	public int getFrequencyOf(T anEntry)
	{
		int frequency = 0;
		int loopCounter = 0;
		Node currentNode = firstNode;
		
		while ((loopCounter < numberOfEntries) && (currentNode != null))
		{
			if (anEntry.equals(currentNode.data))
				frequency++;
			
			loopCounter++;
			currentNode = currentNode.getNextNode();
		}
		
		return frequency;
	}
	
	public boolean contains(T anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		
		while(!found && (currentNode != null))
		{
			if(anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		}
		
		return found;
	}
	
	public T remove()
	{
		T result = null;
		
		if(firstNode != null)
		{
			result = firstNode.getData();
			firstNode = firstNode.next;
			numberOfEntries--;
		}
		
		return result;
	}
	
	private Node getReferenceTo(T anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		
		while(!found && (currentNode != null))
		{
			if(anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.getNextNode();			
		}
		
		return currentNode;
	}
	
	public boolean remove(T anEntry)
	{
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);
		
		if(nodeN != null)
		{
			nodeN.data = firstNode.data;	//Replace located entry with entry
											//in first node
			firstNode = firstNode.next;		//Remove first node
			numberOfEntries--;
			result = true;
		}
		
		return result;
	}
	
	public void clear()
	{
		while(!isEmpty())
			remove();
	}
	
	public void clear()
	{
		firstNode = null;
	}
	
}