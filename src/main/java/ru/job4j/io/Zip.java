package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method creates a zip file, containing a single file. The result is new created zip file.
     *
     * @param source
     * @param target
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        validateArgs(args);
        Zip zip = new Zip();
        ArgsName zipArgs = ArgsName.of(args);
        Path root = Path.of(zipArgs.get("d"));
        String exclude = zipArgs.get("e");
        String output = zipArgs.get("o");
        System.out.println("Directory is: " + zipArgs.get("d"));
        System.out.println("Exclude: " + zipArgs.get("e"));
        System.out.println("Save zip to: " + zipArgs.get("o"));
        List<Path> paths = Search.search(root, p -> !p.toFile().getName().endsWith(exclude));
        zip.packFiles(paths, new File(output));

    }

    public static void validateArgs(String[] args) {
        ArgsName zipArgs = ArgsName.of(args);
        if (args.length < 3) {
            throw new IllegalArgumentException("Program arguments are missing. First argument is Directory folder to be archived. "
                    + "Second is file extension to be excluded. Third argument is file to archive to");
        }
        if (!Files.isDirectory(Paths.get(zipArgs.get("d")))) {
            throw new IllegalArgumentException("Directory folder to be archived is invalid");
        }
        if (!zipArgs.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Filename to archive to is invalid");
        }
    }
}

