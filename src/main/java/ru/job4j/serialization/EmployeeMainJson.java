package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

public class EmployeeMainJson {
    public static void main(String[] args) {
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        String[] skills = {"Java", "SQL", "JS", "Design Pattern",
                "Microservices", "Rest API", "Hibername", "Spring",
                "Kafka", "Maven", "Gradle", "Docker", "Git"};
        JSONArray jsonSkills = new JSONArray(skills);
        final Employee employee = new Employee("John Doe", false, 24, new Contact("111-11-11"), skills);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", employee.getName());
        jsonObject.put("employed", employee.isEmployed());
        jsonObject.put("age", employee.getAge());
        jsonObject.put("contact", employee.getContact());
        jsonObject.put("skills", employee.getSkills());
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(employee).toString());
    }
}
