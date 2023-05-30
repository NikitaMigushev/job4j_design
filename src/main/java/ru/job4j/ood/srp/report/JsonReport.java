package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.List;
import java.util.function.Predicate;

public class JsonReport implements Report {
    private final Store store;
    private final DateTimeParser dateTimeParser;

    public JsonReport(Store store, DateTimeParser dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Employee.class, (com.google.gson.JsonSerializer<Employee>) (src, typeOfSrc, context) -> {
            com.google.gson.JsonObject jsonObject = new com.google.gson.JsonObject();
            jsonObject.addProperty("name", src.getName());
            jsonObject.addProperty("hired", dateTimeParser.parse(src.getHired()));
            jsonObject.addProperty("fired", dateTimeParser.parse(src.getFired()));
            jsonObject.addProperty("salary", src.getSalary());
            return jsonObject;
        });
        Gson gson = gsonBuilder.create();
        return gson.toJson(employees);
    }
}
