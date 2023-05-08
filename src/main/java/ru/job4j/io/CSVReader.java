package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        String filePath = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String outputFilePath = argsName.get("out");
        List<String> filterColumns = Arrays.asList(argsName.get("filter").split(","));
        List<Integer> filteredIndices = new ArrayList<>();
        List<String> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(Path.of(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            boolean scannerHeaderLine = true;
            while (scanner.hasNextLine()) {
                if (scannerHeaderLine) {
                    List<String> headers = Arrays.asList(scanner.nextLine().split(delimiter));
                    for (String filterColumn : filterColumns) {
                        for (int i = 0; i < headers.size(); i++) {
                            if (filterColumn.equals(headers.get(i))) {
                                filteredIndices.add(i);
                                break;
                            }
                        }
                    }
                    StringJoiner sj = new StringJoiner(delimiter);
                    for (int i = 0; i < filteredIndices.size(); i++) {
                        sj.add(headers.get(filteredIndices.get(i)));
                    }
                    result.add(sj.toString());
                    scannerHeaderLine = false;
                } else {
                    String[] line = scanner.nextLine().split(delimiter);
                    StringJoiner sj = new StringJoiner(delimiter);
                    for (int i = 0; i < filteredIndices.size(); i++) {
                        sj.add(line[filteredIndices.get(i)]);
                    }
                    result.add(sj.toString());
                }
            }
            if (outputFilePath.endsWith(".csv")) {
                for (String line : result) {
                    writer.write(line);
                    writer.newLine();
                }
            } else {
                for (String line : result) {
                    System.out.println(line);
                }
            }
        }
    }

    private static void validateArgs(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        if (args.length < 4) {
            throw new IllegalArgumentException("Program arguments are missing. First argument is path to .csv file to read."
                    + "Second is delimeter. Third is output mode: either filepath or stdout. And fourth is filter columns");
        }
        if (!Files.exists(Path.of(argsName.get("path")))) {
            throw new IllegalArgumentException("Path should be directory");
        }
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("File to read from should be .csv extension");
        }
        if (!",".equals(argsName.get("delimiter")) && !";".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException("Delimiter should be either \",\" or \";\" ");
        }
        if (!"stdout".equals(argsName.get("out")) && !argsName.get("out").endsWith(".csv")) {
            throw new IllegalArgumentException("Output should be either \"stdout\" or it should be filename with .csv extension");
        }
        Scanner scanner = new Scanner(Path.of(argsName.get("path")));
        List<String> headers = Arrays.asList(scanner.nextLine().split(argsName.get("delimiter")));
        List<String> filterColumns = Arrays.asList(argsName.get("filter").split(","));
        if (filterColumns.size() > 0) {
            for (String filterColumn : filterColumns) {
                if (!headers.contains(filterColumn)) {
                    String message = String.format("this header \"%s\" does not exits in .csv file", filterColumn);
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        validateArgs(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
