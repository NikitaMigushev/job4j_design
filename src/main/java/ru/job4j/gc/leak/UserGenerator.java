package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    public final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    public final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    public final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    public final String SEPARATOR = " ";
    public final Integer NEW_USERS = 1000;

    public List<String> names;
    public List<String> surnames;
    public List<String> patrons;
    private List<User> users = new ArrayList<>();
    private Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < NEW_USERS; i++) {
            StringBuilder stringBuilder = new StringBuilder(100);
            stringBuilder.append(surnames.get(random.nextInt(surnames.size())))
                    .append(SEPARATOR)
                    .append(names.get(random.nextInt(names.size())))
                    .append(SEPARATOR)
                    .append(patrons.get(random.nextInt(patrons.size())));
            users.add(new User(stringBuilder.toString()));
        }
    }

    private void readAll() {
        try {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

    public List<User> getUsers() {
        return users;
    }

    public void clearUsers() {
        users.clear();
    }
}
