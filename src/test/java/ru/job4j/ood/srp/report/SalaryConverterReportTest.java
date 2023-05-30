package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class SalaryConverterReportTest {
    @Test
    public void whenSalaryConverterReportGenerate() {
        MemStore store = new MemStore();
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report report = new SalaryConverterReport(store, currencyConverter, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary (RUB); Salary (USD); Salary (EUR)")
                .append(System.lineSeparator())
                .append(String.format("Ivan %s %s %s %s %s",
                        parser.parse(worker.getHired()),
                        parser.parse(worker.getFired()),
                        worker.getSalary(),
                        currencyConverter.convert(Currency.RUB, worker.getSalary(), Currency.USD),
                        currencyConverter.convert(Currency.RUB, worker.getSalary(), Currency.EUR)
                ))
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }
}