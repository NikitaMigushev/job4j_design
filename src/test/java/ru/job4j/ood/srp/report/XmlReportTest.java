package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class XmlReportTest {
    @Test
    void testGenerate() {
        Store store = new MemStore();
        DateTimeParser parser = new ReportDateTimeParser();
        DateTimeParser dateTimeParser = new ReportDateTimeParser();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("John Doe", now, now, 5000.0));
        store.add(new Employee("Jane Smith", now, now, 6000.0));
        XmlReport xmlReport = new XmlReport(store, dateTimeParser);
        String xml = xmlReport.generate(employee -> true);
        assertThat(xml).contains("<employee>", "John Doe", "Jane Smith");
    }
}