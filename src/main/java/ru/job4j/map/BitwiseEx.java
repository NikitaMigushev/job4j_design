package ru.job4j.map;

public class BitwiseEx {
    public static void main(String[] args) {
        int a = 5;
        int b = 30;
        int c = a & b;
        System.out.println("A: " + Integer.toBinaryString(a));
        System.out.println("B: " + Integer.toBinaryString(b));
        System.out.println("C: " + Integer.toBinaryString(c));
        System.out.println(c);
    }
}
