package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "СТОП";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> botPhrases = readPhrases();
        List<String> log = new ArrayList<>();
        boolean exitChat = false;
        boolean botIsActive = true;

        System.out.println("Добро пожаловать в консольный чат!");
        while (!exitChat) {
            System.out.print("Вы: ");
            String userInput = scanner.nextLine();
            log.add(getCurrentTimeStamp() + " Вы: " + userInput);
            if (userInput.equals(OUT)) {
                System.out.println("До встречи!");
                saveLog(log);
                exitChat = true;
            } else if (userInput.equals(STOP)) {
                botIsActive = false;
                System.out.println("ЧатБот: ответы ЧатБота деактивированы");
            } else if (userInput.equals(CONTINUE)) {
                botIsActive = true;
                System.out.println("ЧатБот: ответы ЧатБота активированы");
            } else {
                if (botIsActive) {
                    String botAnwer = botPhrases.get(new Random().nextInt(botPhrases.size()));
                    System.out.println("ЧатБот: " + botAnwer);
                    log.add(getCurrentTimeStamp() + " ЧатБот: " + botAnwer);
                }
            }
        }
    }

    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
    }

    private List<String> readPhrases() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, Charset.forName("UTF-8")))) {
            for (String line : log) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Log saved successfuly");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/consoleChatLog.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
