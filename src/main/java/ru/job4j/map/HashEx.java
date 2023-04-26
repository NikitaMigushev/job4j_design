package ru.job4j.map;

import java.util.HashMap;

public class HashEx {
    public static void main(String[] args) {
        String str = "Hello, World!";
        int hash = str.hashCode();
        System.out.println(hash);
        HashMap<String, Integer> grades = new HashMap<>();
        String alice = "Alice";
        String bob = "Bob";
        String charlie = "Charlie";
        grades.put(alice, 85);
        grades.put(bob, 90);
        grades.put(charlie, 95);
        int hashCodeAlice = alice.hashCode();
        int hasCodeBob = bob.hashCode();
        int hasCodeCharlie = charlie.hashCode();
        int hashA = hashCodeAlice ^ (hashCodeAlice >>> 16);
        int hashB = hasCodeBob ^ (hasCodeBob >>> 16);
        int hashC = hasCodeCharlie ^ (hasCodeCharlie >>> 16);
        int bucketA = hashA & 15;
        int bucketB = hashB & 15;
        int bucketC = hashB & 15;
        for (String s : grades.keySet()) {
            System.out.println(s);
        }
    }
}
