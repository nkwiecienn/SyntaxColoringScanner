package main;

import coloring.Coloring;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        Coloring coloring = new Coloring("src/main/java/main/QuickSort.java");
        coloring.print();
        File f = new File(".\\Output");
        f.mkdir();
        coloring.printToHtml("Output/Colored Code.html");
    }
}

