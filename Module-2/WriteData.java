/*
 * Name: Crystal Long
 * Assignment: Module 2 Programming Assignment
 * Date: 08/20/2025
 *
 * This program generates an array of five random integers and five random doubles,
 * then writes/appends them into a file named "CrystalLong_datafile.dat".
 */

import java.io.*;
import java.util.Random;

public class WriteData {
    public static void main(String[] args) {
        String fileName = "CrystalLong_datafile.dat";
        Random rand = new Random();

        // Generate random integers
        int[] intArray = new int[5];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = rand.nextInt(100); // random integer between 0-99
        }

        // Generate random doubles
        double[] doubleArray = new double[5];
        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = rand.nextDouble() * 100; // random double between 0-99
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println("Integers:");
            for (int num : intArray) {
                writer.print(num + " ");
            }
            writer.println();

            writer.println("Doubles:");
            for (double num : doubleArray) {
                writer.print(num + " ");
            }
            writer.println();
            writer.println("----------------------");
            
            System.out.println("Data written/appended to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }
}
