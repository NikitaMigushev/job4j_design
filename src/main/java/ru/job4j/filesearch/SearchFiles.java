package ru.job4j.filesearch;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFiles implements FileVisitor<Path> {
    private final String searchType;
    private final String searchQuery;
    private List<Path> paths = new ArrayList<>();

    public SearchFiles(String searchQuery, String searchType) {
        this.searchQuery = searchQuery;
        this.searchType = searchType;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.getFileName().toString();
        if ("name".equals(searchType)) {
            if (fileName.equals(searchQuery)) {
                paths.add(file);
            }
        } else if ("mask".equals(searchType)) {
            if (matchesMask(fileName)) {
                paths.add(file);
            }
        } else if ("regex".equals(searchType)) {
            if (matchesRegex(fileName)) {
                paths.add(file);
            }
        }
        return FileVisitResult.CONTINUE;
    }

    private boolean matchesRegex(String fileName) {
        Pattern regexPattern = Pattern.compile(searchQuery);
        Matcher matcher = regexPattern.matcher(fileName);
        return matcher.find();
    }

    private boolean matchesMask(String fileName) {
        String regex = searchQuery.replace(".", "\\.").replace("*", ".*").replace("?", ".");
        return fileName.matches(regex);
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException e) throws IOException {
        System.err.println("Failed to access file: " + file + " (" + e.getMessage() + ")");
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
