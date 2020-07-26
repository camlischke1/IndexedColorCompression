/**Cam Lischke Program 4 CSC 221 Dr. Burg
 *
 * This class has been adjusted to fit the needs of a hash table that uses chaining to store RGB pixels based on their color.
 * They key in this instance is the three bit color that arises from the RGB values within an image's pixel. It stores these pixels if it does not already exist,
 * but if it does exist, it adds one to the frequency value for that specific colored pixel.
 *
 * @author Cam Lischke <lisccd18@wfu.edu>
 */


package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


// SeparateChaining Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// void makeEmpty( )      --> Remove all items

/**
 * Separate chaining table implementation of hash tables.
 * Note that all "matching" is based on the equals method.
 * @author Mark Allen Weiss
 */
public class SeparateChainingHashTable<AnyType> {

    /**Construct the hash table.
     */
    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    /**Construct the hash table.
     * @param size approximate table size.
     */
    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++)
            theLists[i] = new LinkedList<Pixel>();
    }

    /**Insert into the hash table. If the item is already present, then increase frequency by 1
     * CAM LISCHKE ADJUSTED THIS METHOD
     * @param x the item to insert.
     */
    public void insert(Pixel x) {
        List<Pixel> whichList = theLists[hash(x, theLists.length)];
        if (this.contains(x) == -1) {
            whichList.add(x);

            // Rehash; see Section 5.5
            if (++currentSize > theLists.length)
                rehash();
        }
        else if (this.contains(x) > -1){
            whichList.get(this.contains(x)).addFreq();
        }
    }

    /**
     * Remove from the hash table.
     *
     * @param x the item to remove.
     */
    public void remove(Pixel x) {
        List<Pixel> whichList = theLists[hash(x, theLists.length)];
        if (whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    /**
     * Find an item in the hash table.
     *
     * @param x the item to search for.
     * @return true if x isnot found.
     */
    public int contains(Pixel x) {
        List<Pixel> whichList = theLists[hash(x, theLists.length)];
        boolean contains = false;
        int i;
        for (i = 0; i < whichList.size(); i++){
            if (whichList.get(i).equals(x)) {
                contains = true;
                break;
            }
        }
        if (contains){
            return i;
        }
        else
            return -1;

    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++)
            theLists[i].clear();
        currentSize = 0;
    }

    private void rehash() {
        List<Pixel>[] oldLists = theLists;

        // Create new double-sized, empty table
        theLists = new List[nextPrime(2 * theLists.length)];
        for (int j = 0; j < theLists.length; j++)
            theLists[j] = new LinkedList<Pixel>();

        // Copy table over
        currentSize = 0;
        for (List<Pixel> list : oldLists) {
            for (Pixel item : list)
                insert(item);
        }
    }


    private static final int DEFAULT_TABLE_SIZE = 101;

    /**
     * The array of Lists.
     */
    private List<Pixel>[] theLists;
    private int currentSize;

    /**
     * Internal method to find a prime number at least as large as n.
     *
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;

        for (; !isPrime(n); n += 2)
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     *
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;

        if (n == 1 || n % 2 == 0)
            return false;

        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }

    
    /**CAM LISCHKE'S PRINT METHOD FOR THE HASH TABLE**/
    public void printTest(){
        int numPixels = 0;
        for (int i = 0; i <theLists.length; i++) {
            System.out.print("Index " + i + ": ");

            for (int j = 0; j < theLists[i].size(); j++) {
                System.out.print(theLists[i].get(j).getColor() + "(freq: " + theLists[i].get(j).getFreq() + "), ");
                numPixels += theLists[i].get(j).getFreq();
            }

            System.out.println();
        }
        System.out.println("\nThe number of pixels read and inserted are: " + numPixels);
    }

    /** I EVOLVED THIS CODE TO FIT MY NEEDS
     *
             * @param p      the Pixel to hash
     * @param tableSize the size of the hash table.
     * @return the hash value.
     */
    public int hash(Pixel p, int tableSize) {
        int hashVal = (int)(p.getColor() % tableSize);
        if (hashVal < 0)
            hashVal += tableSize;

        return hashVal;
    }

    public int size(){
        return theLists.length;
    }

    //GET THE TOP Nth FREQUENCIES AND PUT INTO A TABLE
    public ArrayList<Pixel> intoFreqTable(int userInputtedSize){
        ArrayList<Pixel> freqTable = new ArrayList<Pixel>();
        for (List list : theLists){
            for (int i = 0; i < list.size(); i++){
                if (list.get(i) != null){
                    freqTable.add((Pixel) list.get(i));
                }
            }
        }
        Collections.sort(freqTable);

        if (userInputtedSize > freqTable.size())
            userInputtedSize = freqTable.size() - 1;

        ArrayList<Pixel> topNFreq = new ArrayList<Pixel>();
        for (int i = userInputtedSize; i > 0; i--){
            topNFreq.add(freqTable.get(i));
        }
        return topNFreq;
    }

}
