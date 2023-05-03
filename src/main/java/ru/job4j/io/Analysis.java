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
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)
                     ))) {
            String line;
            boolean start = false;
            while ((line = in.readLine()) != null) {
                String[] checkLine = line.split(" ");
                if (!start && "400".equals(checkLine[0]) || "500".equals(checkLine[0])) {
                    start = true;
                    logLine.add(checkLine[1]);
                } else if (start) {
                    start = false;
                    logLine.add(checkLine[1]);
                    log.add(logLine.toString());
                    logLine = new StringJoiner(";");
                }
            }
            for (String l : log) {
                out.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
