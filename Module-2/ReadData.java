/*
 * Name: Crystal Long
 * Assignment: Module 2 Programming Assignment
 * Date: 08/20/2025
 *
 * This program reads data from "CrystalLong_datafile.dat"
 * and displays it to the console.
 */

import java.io.*;

public class ReadData {
    public static void main(String[] args) {
        String fileName = "CrystalLong_datafile.dat";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Reading data from " + fileName + ":\n");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Run WriteData first to create the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
