package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        /*Create pattern*/
        Pattern pattern = Pattern.compile("Я учусь на Job4j");
        String text1 = "Я учусь на Job4j";

        /*Create matcher of pattern with text*/
        Matcher matcher1 = pattern.matcher(text1);

        /*Check if pattern is present in text*/
        boolean isPresent1 = matcher1.matches();

        System.out.println(isPresent1);

        String text2 = "Я учусь на курсе Job4j";
        /*Create second matcher of pattern with another text*/
        Matcher matcher2 = pattern.matcher(text2);

        /*Check our second matcher with another text*/
        boolean isPresent2 = matcher2.matches();
        System.out.println(isPresent2);

        Pattern pattern1 = Pattern.compile("Job4j");
        String text3 = "Job4j";
        Matcher matcher3 = pattern1.matcher(text3);
        boolean isPresent3 = matcher3.matches();
        System.out.println(isPresent3);

        String text4 = "job4j";
        Matcher matcher4 = pattern1.matcher(text4);
        boolean isPresent4 = matcher4.matches();
        System.out.println(isPresent4);

        Pattern pattern2 = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);
        Matcher matcher5 = pattern2.matcher(text4);
        System.out.println(matcher5.matches());

        Matcher matcher6 = pattern2.matcher(text1);
        System.out.println(matcher6.find());

        String text = "Job4j и Job4j и Job4j";
        Matcher matcher7 = pattern2.matcher(text);
        while (matcher7.find()) {
            System.out.println("Найдено совпадение: " + matcher7.group());
            System.out.println("start: " + matcher7.start());
            System.out.println("end: " + matcher7.end());
        }
        String rsl = matcher7.replaceAll("111");
        System.out.println(rsl);
    }
}
