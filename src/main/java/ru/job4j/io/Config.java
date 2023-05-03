package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Method reads file and adds key-value pairs to values Map. Method skips comments and empty lines.
     */
    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (!line.startsWith("#") && !line.startsWith("/") && !line.isBlank()) {
                    String[] keyValue = line.split("=");
                    if (keyValue.length < 2 || keyValue[0].isBlank() || keyValue[1].isBlank()) {
                        throw new IllegalArgumentException();
                    }
                    values.put(keyValue[0], keyValue[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("data/appWithComments.properties");
        config.load();
    }
}
