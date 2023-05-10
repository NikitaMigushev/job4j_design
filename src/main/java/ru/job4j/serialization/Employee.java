package ru.job4j.serialization;

import java.util.Arrays;

public class Employee {
    private String name;
    private boolean employed;
    private int age;
    private Contact contact;
    private String[] skills;

    public Employee(String name, boolean employed, int age, Contact contact, String[] skills) {
        this.name = name;
        this.employed = employed;
        this.age = age;
        this.contact = contact;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", employed=" + employed
                + ", age=" + age
                + ", contact=" + contact
                + ", skills=" + Arrays.toString(skills)
                + '}';
    }
}


