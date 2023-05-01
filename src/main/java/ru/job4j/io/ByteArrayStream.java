package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayStream {
    public static void main(String[] args) {
        byte[] bytes = new byte[] {'J', 'a', 'v', 'a'};
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        int data;
        while ((data = stream.read()) != -1) {
            System.out.println((char) data);
        }
        System.out.println();
        String str = "123456789";
        byte[] bytes1 = str.getBytes();
        ByteArrayInputStream byteStream2 = new ByteArrayInputStream(bytes1, 3, 4);
        int data1;
        while ((data1 = byteStream2.read()) != -1) {
            System.out.println((char) data1);
        }
        System.out.println();
        ByteArrayOutputStream outputStream =  new ByteArrayOutputStream();
        byte[] bytes2 = "Message".getBytes();
        try (FileOutputStream fileOutputStream = new FileOutputStream("data/message.txt")) {
            outputStream.writeTo(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
