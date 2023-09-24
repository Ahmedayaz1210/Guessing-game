package BagPackage;

import java.util.Arrays;

public final class ArrayBag<T> implements BagInterface<T> {
    private T[] bag;
    private int numOfEntries;
    private static int DEFAULT_CAPACITY;
    private boolean integrityOk;
    private static final int MAX_CAPACITY = 10000;

    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayBag(int desiredCapacity) {
    	integrityOk = false;
    	if(desiredCapacity <= MAX_CAPACITY) {
    		@SuppressWarnings("unchecked")
            T[] tempBag = (T[]) new Object[desiredCapacity];
            bag = tempBag;
            numOfEntries = 0;
            integrityOk = true;
    	}
    	else {
    		throw new IllegalStateException("Attempt to create a bag whose " + "capacity exceeds allowed maximum.");
    	}
        
    }
    
    public static void setNumbers(int m) {
    	DEFAULT_CAPACITY = m; // Set the value of numbers using m
    }

    @Override
    public int getCurrentSize() {
        return numOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numOfEntries == 0;
    }

    @Override
    public boolean add(T newEntry) {
    	checkIntegrity();
        
        if(isArrayFull()) {
        	doubleCapacity();
        }
        bag[numOfEntries] = newEntry;
        numOfEntries++;
        
        return true;
    }
    
    private void checkCapacity(int capacity) {
    	if(capacity > MAX_CAPACITY) {
    		throw new IllegalStateException("Attempt to create a bag whose " + "capacity exeeds allowed " + "maximum of " + MAX_CAPACITY);
    	}
    }
    
    private void doubleCapacity() {
    	int newLength = 2 * bag.length;
    	checkCapacity(newLength);
    	bag = Arrays.copyOf(bag, newLength);
    }

    @Override
    public T remove() {
        checkIntegrity();
        T result = removeEntry(numOfEntries-1);
        return result;
    }

    @Override
    public boolean remove(T anEntry) {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }
    
    private T removeEntry(int givenIndex) {
    	T result = null;
    	if(!isEmpty() && givenIndex >= 0) {
    		result = bag[givenIndex];
    		bag[givenIndex] = bag[numOfEntries-1];
    		bag[numOfEntries-1] = null;
    		numOfEntries--;
    	}
    	return result;
    }
    
    private int getIndexOf(T anEntry) {
    	int where = -1;
    	boolean stillLooking = true;
    	int index = 0;
    	while(stillLooking && (index < numOfEntries)) {
    		if(anEntry.equals(bag[index])) {
    			stillLooking = false;
    			where = index;
    		}else {
    			index++;
    		}
    	}
    	return where;
    }

    @Override
    public void clear() {
        while(!isEmpty()) {
        	remove();
        }
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        checkIntegrity();
        int counter = 0;
        for(int i = 0; i < numOfEntries; i++) {
        	if(anEntry.equals(bag[i])) {
        		counter++;
        	}
        }
        return counter;
    }

    @Override
    public boolean contains(T anEntry) {
    	checkIntegrity();
    	return getIndexOf(anEntry) > -1;
    }

    @Override
    public T[] toArray() {
    	checkIntegrity();
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numOfEntries];
        for(int i = 0; i < numOfEntries; i++) {
        	result[i] = bag[i];
        }
        return result;
    }
    
    private boolean isArrayFull() {
    	return numOfEntries >= bag.length;
    }
    private void checkIntegrity()
    {
     if (!integrityOk)
     throw new SecurityException("ArrayBag object is corrupt.");
    }
}
