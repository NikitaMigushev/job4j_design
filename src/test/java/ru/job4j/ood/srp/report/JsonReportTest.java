package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class JsonReportTest {
    @Test
    void testGenerate() {
        Store store = new MemStore();
        store.add(new Employee("John Doe", Calendar.getInstance(), Calendar.getInstance(), 5000.0));
        store.add(new Employee("Jane Smith", Calendar.getInstance(), Calendar.getInstance(), 6000.0));
        JsonReport jsonReport = new JsonReport(store, new ReportDateTimeParser());
        String json = jsonReport.generate(employee -> true);
        assertThat(json).contains("John Doe", "Jane Smith");
    }
}