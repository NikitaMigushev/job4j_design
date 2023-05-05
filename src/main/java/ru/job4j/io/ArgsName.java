package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    /**
     * Method gets value from key after checking key value for format.
     *
     * @param key
     * @return value
     */
    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException("This key: " + "'" + key + "'" + " is missing");
        }
        return values.get(key);
    }

    /**
     * Method puts reads String[], parse it into key-value pares and puts them into values Map.
     *
     * @param args
     */
    private void parse(String[] args) {
        for (String arg : args) {
            checkArg(arg);
        }
        for (String arg : args) {
            String[] parseArg = arg.split("=", 2);
            String key = parseArg[0].split("-")[1];
            String value = parseArg[1];
            values.put(key, value);
        }
    }

    /**
     * Method creates new values Map by parsing String[] args after checking key value for format
     * and puts into values map.
     *
     * @param args
     * @return
     */
    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    /**
     * Method checks for proper argument format. String starts with "-", first "=" is delimiter.
     * Key and values should be present.
     *
     * @param arg
     * @return
     */
    private static boolean checkArg(String arg) {
        if (!arg.contains("=")) {
            throw new IllegalArgumentException("Error: This argument \'" + arg + "\' does not contain an equal sign");
        }
        String[] checkArg = arg.split("=");
        if (!checkArg[0].startsWith("-")) {
            throw new IllegalArgumentException("Error: This argument \'" + arg + "\' does not start with a '-' character");
        }
        if (checkArg[0].split("-").length < 2) {
            throw new IllegalArgumentException("Error: This argument \'" + arg + "\' does not contain a key");
        }
        if (checkArg.length < 2) {
            throw new IllegalArgumentException("Error: This argument \'" + arg + "\' does not contain a value");
        }
        return true;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[]{
                "-out=project.zip", "-encoding=UTF-8"
        });
        System.out.println(zip.get("out"));
//        ArgsName argsName = ArgsName.of(new String[]{});
        ArgsName anotherArgs = ArgsName.of(new String[]{"-Xmx=512", "-=?msg=Exit="});


    }

    /**
     * Program Logic is the following:
     * User creates ArgsName Object.
     * Invokes method  of(String[] args), passes array of strings in certain format.
     * In method of parse method is invoked and parses array into key-vaulue pairs delimiting by
     * first "-" char and first "=" char. after "-" and before "=" is key, after "=" is value.
     * Format is checked so that key and value are present. If not, throw exception.
     * When user wants to retreive value he invokes get method. In get method the string format is checked
     * and values is retrieved.
     */
}
