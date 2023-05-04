package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Path> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fp = new FileProperty(attrs.size(), file.getFileName().toString());
        if (files.containsKey(fp) && !Files.isSameFile(files.get(fp), file)) {
            System.out.println("Duplicate file found: " + file.toAbsolutePath() + " and " + files.get(fp).toAbsolutePath());
        } else {
            files.put(fp, file);
        }
        return super.visitFile(file, attrs);
    }
}
