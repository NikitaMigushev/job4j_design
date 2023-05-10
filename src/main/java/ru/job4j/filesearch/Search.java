package ru.job4j.filesearch;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Class finds file based on given parameters. Before running the program, make sure to fill up program arguments:
 * -d=* - directory to start search from." +
 * -n=* - search query.
 * -t=[name, mask or regex] - type of search: filename, mask or regex." +
 * -o=*.txt - output file path
 * Example: -d=./ -n=^Search -t=regex -o=./data/searchResult.txt
 */
public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        ArgsName argsName = ArgsName.of(args);
        System.out.println("Arguments have been validated");
        String root = argsName.get("d");
        String searchQuery = argsName.get("n");
        String searchType = argsName.get("t");
        String outputFile = argsName.get("o");
        List<Path> result = search(Path.of(root), searchQuery, searchType);
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsName.get("o"))))) {
            String ls = System.lineSeparator();
            out.println("Based on given parameters:" + ls
            + "Root: " + root + ls
            + "SearchQuery: " + searchQuery + ls
            + "SearchType: " + searchType + ls
            + "Output file: " + outputFile);
            if (result.size() == 0) {
                out.println("No files have been found");
            } else {
                out.println("These files have been found");
                for (Path path : result) {
                    out.println(path.toString());
                }
            }
            System.out.println("Search results have been saved in " + Path.of(outputFile).toString());
        }
    }

    public static List<Path> search(Path root, String searchQuery, String searchType) {
        SearchFiles searcher = new SearchFiles(searchQuery, searchType);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    private static void validateArgs(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Program arguments are missing. The first argument is directory to start search from."
                    + "Second argument is Search query. Third argument is type of search: filename, mask or regex."
                    + "And final argument is output file path");
        }
        ArgsName argsName = ArgsName.of(args);
        if (!Files.isDirectory(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException("First argument Root folder to start search from is invalid");
        }
        String searchQuery = argsName.get("n");
        if (!isFileName(searchQuery) && !isMask(searchQuery) && !isRegEx(searchQuery)) {
            throw new IllegalArgumentException("Search query should be either filename, mask or regular expression");
        }
        String searchType = argsName.get("t");
        if (!"name".equals(searchType) && !"mask".equals(searchType) && !"regex".equals(searchType)) {
            throw new IllegalArgumentException("Search type should be either \"name\", \"mask\" or \"regex\"");
        }
        if (!argsName.get("o").endsWith(".txt")) {
            throw new IllegalArgumentException("Out file should be .txt extension");
        }
    }

    private static boolean isFileName(String input) {
        File file = new File(input);
        return file.isFile();
    }

    private static boolean isMask(String input) {
        return input.contains("*") || input.contains("?");
    }

    private static boolean isRegEx(String input) {
        boolean rsl = false;
        try {
            Pattern.compile(input);
            rsl = true;
        } catch (PatternSyntaxException e) {
            rsl = false;
        }
        return rsl;
    }
}
