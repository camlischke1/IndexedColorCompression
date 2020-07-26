/**Cam Lischke Program 4 CSC 221 Dr. Burg
 *
 * This class keeps track of a pixel image, its original index in the frequency table of the n-most frequent colors within the image, and a
 * squaredDifference variable that tracks the "distance" between the pixel being compared and the pixel within the frequency table. It is implemented
 * in a sorted list that relays the closest color already used in the frequency table for the "popularity algorithm"
 *
 * @author Cam Lischke <lisccd18@wfu.edu>
 */


package com.company;

public class ClosestColor implements Comparable<ClosestColor>{
    private long squaredDifference;
    private int indexInFreqTable;
    private Pixel containedPixel;

    public ClosestColor(int index, Pixel p, Pixel comparison){
        indexInFreqTable = index;
        containedPixel = p;
        squaredDifference = (long)(Math.pow((comparison.getRed() - p.getRed()), 2.0) + Math.pow((comparison.getGreen() - p.getGreen()), 2.0) + Math.pow(comparison.getBlue() - p.getBlue(), 2.0));
    }

    public Pixel getPixel(){
        return containedPixel;
    }

    public long getSquaredDifference(){
        return squaredDifference;
    }

    public int getIndexInFreqTable(){
        return indexInFreqTable;
    }

    public int compareTo(ClosestColor cc){
        if (squaredDifference > cc.getSquaredDifference()) return 1;
        else if (squaredDifference < cc.getSquaredDifference()) return -1;
        else return 0;
    }

}
