package main;

import scanner.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner("src/main/java/scanner/Scanner.java");
        scanner.scanAndPrintTokens();
    }
}
