package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UserMain {
    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, 1987);
        birthday.set(Calendar.MONTH, Calendar.OCTOBER);
        birthday.set(Calendar.DAY_OF_MONTH, 9);
        User userA = new User("Nikita", 0, birthday);
        User userB = new User("Nikita", 0, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(userA, new Object());
        map.put(userB, new Object());
        int hashCodeA = userA.hashCode();
        int hasCodeB = userB.hashCode();
        int hashA = hashCodeA ^ (hashCodeA >>> 16);
        int hashB = hasCodeB ^ (hasCodeB >>> 16);
        int bucketA = hashA & 15;
        int bucketB = hashB & 15;
        for (User u : map.keySet()) {
            System.out.println(u);
        }
        System.out.println(userA.equals(userB));
    }
}
