package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        boolean activeUser = true;
        double height = 1.83;
        char sex = 'M';
        short weight = 80;
        float experience = 5.5f;
        long someRandomNumber = new Random().nextLong();
        LOG.debug("User ingo : {}, age : {}, activeUser : {}, height: {}, sex : {} , weight : {}, experience : {}, random : {}",
                name, age, activeUser, height, sex, weight, experience, someRandomNumber);
    }
}
