package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    private final DirFileCache cache;
    private final Scanner scanner;

    public Emulator() {
        this.cache = new DirFileCache("");
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean run = true;
        while (run) {
            System.out.println("1. Указать кэшируемую директорию");
            System.out.println("2. Загрузить содержимое файла в кэш");
            System.out.println("3. Получить содержимое файла из кэша");
            System.out.println("4. Выйти");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    setCacheDirectory();
                    break;
                case 2:
                    loadFileToCache();
                    break;
                case 3:
                    getFileFromCache();
                    break;
                case 4:
                    System.out.println("Всего хорошего!");
                    run = false;
                default:
                    System.out.println("Неверный выбор");
                    break;
            }
        }
    }

    private void setCacheDirectory() {
        System.out.print("Введите путь к кэшируемой директории: ");
        String cachingDir = scanner.nextLine();
        cache.setCachingDir(cachingDir);
        System.out.println("Кэшируемая директория установлена: " + cachingDir);
    }

    private void loadFileToCache() {
        System.out.println("Введите имя файла для загрузки в кэш: ");
        String fileName = scanner.nextLine();
        String fileContent = cache.get(fileName);
        if (fileContent == null) {
            System.out.println("Не удалось загрузить файл в кэш");
        } else {
            cache.put(fileName, fileContent);
            System.out.println("Файл успешно загружен в кэш");
        }
    }

    private void getFileFromCache() {
        System.out.println("Введите имя файла для получения из кэша: ");
        String fileName = scanner.nextLine();
        String fileContent = cache.get(fileName);
        if (fileContent == null) {
            System.out.println("Файл не найден в кэше");
        } else {
            System.out.println("Содержимое файла: ");
            System.out.println(fileContent);
        }
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        emulator.run();
    }
}
