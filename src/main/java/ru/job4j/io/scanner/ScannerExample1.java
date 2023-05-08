package ru.job4j.io.scanner;

import java.io.CharArrayReader;
import java.util.Scanner;

public class ScannerExample1 {
    public static void main(String[] args) {
        var ls = System.lineSeparator();
        /*Create array of data*/
        var data = String.join(ls,
                "1 A 2",
                "3 4 B",
                "C 5 6");
        /*Create scanner object. Pass to scanner CharArrayReader and pass to that data array transformed into array of charectes*/
        var scanner = new Scanner(new CharArrayReader(data.toCharArray()));
        /*Initiate while loop, till scanner has next value*/
        while (scanner.hasNext()) {
            /*Check if next value is Int*/
            if (scanner.hasNextInt()) {
                /*if yes, print it*/
                System.out.println(scanner.nextInt());
                System.out.println(" ");
            } else {
                /*else, go to next value*/
                scanner.next();
            }
        }
    }
}
