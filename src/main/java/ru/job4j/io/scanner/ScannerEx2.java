package ru.job4j.io.scanner;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ScannerEx2 {
    public static void main(String[] args) {
        var data = "emp@mail.ru, empl2@mail.ru, empl3@mail.ru";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.getBytes());
        var scanner = new Scanner(byteArrayInputStream).useDelimiter(", ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
