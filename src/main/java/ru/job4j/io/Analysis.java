package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Method read Server log file, filter unavailable status time period and saves to target file.
 */
public class Analysis {
    public void unavailable(String source, String target) {
        List<String> log = new ArrayList<>();
        StringJoiner logLine = new StringJoiner(";");
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String line;
            int start = 0;
            while ((line = in.readLine()) != null) {
                String[] checkLine = line.split(" ");
                if (start == 0 && "400".equals(checkLine[0]) || start == 0 && "500".equals(checkLine[0])) {
                    start = 1;
                    logLine.add(checkLine[1]);
                } else if (start == 1 && !"400".equals(checkLine[0]) || start == 1 && !"500".equals(checkLine[0])) {
                    start = 0;
                    logLine.add(checkLine[1]);
                    log.add(logLine.toString());
                    logLine = new StringJoiner(";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (String line : log) {
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
