/**Cam Lischke Program 4 CSC 221 Dr. Burg
 *
 * This program implements a simulation of the change of an uncompressed image from RGB mode to indexed color mode. In indexed color mode,
 * a maximum of 256 colors are used. Your program should allow the user to specify N, the number of colors desired, between 1 and 256. You
 * should use the "popularity algorithm," which uses the N most frequently occurring colors from the original image.
 *
 * @author Cam Lischke <lisccd18@wfu.edu>
 */


package com.company;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import static java.lang.Integer.parseInt;
import static java.lang.System.exit;


public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        String givenInput = JOptionPane.showInputDialog("Input the name of the file that you want to use for this Indexed Color File: ");
        int height = parseInt(JOptionPane.showInputDialog("Give the CORRECT height (in pixels) of the image: "));
         int width = parseInt(JOptionPane.showInputDialog("Give the CORRECT width (in pixels) of the image: "));
        String number = JOptionPane.showInputDialog("Input the number of desired colors (between 1 and 256): ");
        int n = parseInt(number);
        if (n < 1 || n > 256) {
            System.out.print("Enter a number between 1 and 256 next time, you fool.");
            exit(-1);
        }


        try {
            InputStream is = new FileInputStream(givenInput);

            DataInputStream input = new DataInputStream(is);

            SeparateChainingHashTable<Pixel> hashTable = new SeparateChainingHashTable<Pixel>(nextPrime(n));

            //NEED TO KNOW THE DIMENSIONS OF THE PICTURE THAT IS BEING READ IN, HARD CODE IT FOR NOW
            for (int i = 0; i < (height * width); i++) {
                Pixel pixel = new Pixel(input.read(), input.read(), input.read());
                hashTable.insert(pixel);
            }

            ArrayList<Pixel> freqTable = hashTable.intoFreqTable(n);
            for(int i = 0; i < freqTable.size(); i++){
                System.out.println("Index: " + i + " R" + freqTable.get(i).getRed() + " G" + freqTable.get(i).getGreen() +" B" + freqTable.get(i).getBlue() +" Frequency: " + freqTable.get(i).getFreq());
            }


            InputStream image = new FileInputStream(givenInput);
            DataInputStream picture = new DataInputStream(image);

            //NEED TO KNOW THE DIMENSIONS OF THE PICTURE, HARD CODE FOR NOW
            int indexes[][] = new int[height][width];
            for (int i = 0; i < height; i++) {                                  //ROW = i must be less than height, COL = j must be less than width
                for (int j = 0; j < width; j++) {
                    Pixel pixel = new Pixel(picture.read(), picture.read(), picture.read());
                    if (getIndexOf(freqTable, pixel) > -1)
                        indexes[i][j] = getIndexOf(freqTable, pixel);
                    else{
                        //FIND CLOSEST RGB TO PIXEL THAT EXISTS IN FREQTABLE
                        ArrayList<ClosestColor> leastSquares = new ArrayList<ClosestColor>();
                        for (int a = 0; a < freqTable.size(); a++) {
                            ClosestColor cc = new ClosestColor(a, pixel, freqTable.get(a));
                            leastSquares.add(cc);
                        }
                        Collections.sort(leastSquares);
                        //FIND THE ELEMENT THAT THIS LEAST SQUARED DIFFERENCE MAPS TO
                        indexes[i][j] = leastSquares.get(0).getIndexInFreqTable();
                    }
                }
            }


            for (int i = 0; i < 25; i++){
                System.out.print(String.format("%10s", ("ROW " + i + ": ")));
                for (int j = 0; j < 25; j++){
                    System.out.print(String.format("%5s", (indexes[i][j])));
                }
                System.out.println();
            }










        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Internal method to find a prime number at least as large as n. This method derived from class SeparateChainingHashTable
     * AUTHOR OF THIS METHOD: @author Mark Allen Weiss
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
     * Internal method to test if a number is prime. This method derived from class SeparateChainingHashTable
     * Not an efficient algorithm.
     * AUTHOR OF THIS METHOD: @author Mark Allen Weiss
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


    /**Finds the index of Pixel p based on my own equals() function within the Pixel class.
     *
     * @param list
     * @param p
     * @return the index of p if it is found, -1 if not
     */
    public static int getIndexOf(ArrayList<Pixel> list, Pixel p){
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).equals(p))
                return i;
        }
        //if it gets to this line, it does not exist in list
        return -1;
    }
}
