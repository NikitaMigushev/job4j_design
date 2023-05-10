package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EmployeeMain {
    public static void main(String[] args) {
        String[] skills = {"Java", "SQL", "JS", "Design Pattern",
                "Microservices", "Rest API", "Hibername", "Spring",
                "Kafka", "Maven", "Gradle", "Docker", "Git"};
        final Employee employee = new Employee("John Doe", false,
                24, new Contact("111-11-11"), skills);
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(employee));

        final String employeeJson = "{"
                + "  \"name\": \"John Doe\","
                + "  \"employed\": false,"
                + "  \"age\": 24,"
                + "  \"contact\": {"
                + "    \"phone\": \"111-11-11\""
                + "  },\n"
                + "  \"skills\": [\"Java\", \"SQL\", \"JS\", \"Design Pattern\","
                + "    \"Microservices\", \"Rest API\", \"Hibername\", \"Spring\","
                + "    \"Kafka\", \"Maven\", \"Gradle\", \"Docker\", \"Git\"]"
                + "}";
        final Employee employee1 = gson.fromJson(employeeJson, Employee.class);
        System.out.println(employee1);
    }
}
