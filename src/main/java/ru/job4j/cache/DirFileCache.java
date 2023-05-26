package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {
    private String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        try {
            Path filePath = Path.of(cachingDir, key);
            byte[] fileBytes = Files.readAllBytes(filePath);
            return new String(fileBytes);
        } catch (IOException e) {
            System.out.println(String.format("Файл: %s не найден в директории: %s ", key, cachingDir));
            return null;
        }
    }

    public void setCachingDir(String dir) {
        cachingDir = dir;
    }
}
