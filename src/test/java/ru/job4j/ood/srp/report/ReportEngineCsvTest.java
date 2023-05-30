package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class ReportEngineCsvTest {
    @Test
    public void whenReportEngineCsvGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee workerA = new Employee("Ivan", now, now, 100);
        Employee workerB = new Employee("Petr", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(workerA);
        store.add(workerB);
        Report reportCsv = new ReportEngineCsv(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(String.format("Ivan; %s; %s; 100.0", parser.parse(workerA.getHired()), parser.parse(workerA.getFired())))
                .append(System.lineSeparator())
         .append(String.format("Petr; %s; %s; 100.0", parser.parse(workerB.getHired()), parser.parse(workerB.getFired())))
                .append(System.lineSeparator());
        assertThat(reportCsv.generate(em -> true)).isEqualTo(expect.toString());
    }
}