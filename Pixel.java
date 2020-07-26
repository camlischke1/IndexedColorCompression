/**Cam Lischke Program 4 CSC 221 Dr. Burg
 *
 * This class stores the RGB value of a pixel within a RAW file. Upon construction, it stored a frequency value, as well as the color of the
 * pixel, one of the possible 2^24 values.
 *
 * @author Cam Lischke <lisccd18@wfu.edu>
 */

package com.company;

public class Pixel implements Comparable<Pixel>{
    private int red;
    private int green;
    private int blue;
    private long color;
    private int freq;

    public Pixel(int r, int g, int b){      //Constructor
        red = r;
        green = g;
        blue = b;
        color = (r * 65536) + (g * 256) + (b);
        freq = 1;
    }

    public int getRed(){ return red; }
    public int getGreen(){ return green; }
    public int getBlue() { return blue; }
    public int getFreq() { return freq; }
    public long getColor() { return color; }

    public void setRed(int r){
        red = r;
    }

    public void setGreen(int g){
        green = g;
    }

    public void setBlue(int b){
        blue = b;
    }

    public void addFreq() { freq++; }

    public void addFreq(int n){ freq += n; }

    public boolean equals(Pixel p){
        return ((red == p.getRed() && green == p.getGreen() && blue == p.getBlue()) || color == p.getColor());
    }

    public int compareTo(Pixel p){
        if (this.freq > p.freq)
            return 1;
        else if (freq == p.freq)
            return 0;
        else
            return -1;
    }


}
