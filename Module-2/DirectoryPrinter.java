// Crystal Long - 
// Mod.2.1 Example of Recursion
// Recursive program to print directory structure and file list

import java.io.File;

public class DirectoryPrinter {

    // Recursive method to print files/folders
    public static void printDirectory(File dir, int depth) {
        // Print indentation for tree structure
        for (int i = 0; i < depth; i++) {
            System.out.print("  "); // two spaces per level
        }

        // Print current directory or file name
        System.out.println(dir.getName());

        // If it's a folder, list its contents
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    // Recursively call for each child
                    printDirectory(file, depth + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Change this to any directory path you want to explore
        File root = new File("C:\\Users\\K1tt3\\csd");

        if (root.exists()) {
            System.out.println("Directory structure of: " + root.getAbsolutePath());
            printDirectory(root, 0);
        } else {
            System.out.println("Directory does not exist!");
        }
    }
}
