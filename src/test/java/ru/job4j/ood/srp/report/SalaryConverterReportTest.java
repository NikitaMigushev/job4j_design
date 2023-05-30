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
        Employee workerA = new Employee("Ivan", now, now, 100);
        Employee workerB = new Employee("Petr", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(workerA);
        store.add(workerB);
        Report report = new SalaryConverterReport(store, currencyConverter, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary (RUB); Salary (USD); Salary (EUR)")
                .append(System.lineSeparator())
                .append(String.format("Ivan %s %s %s %s %s",
                        parser.parse(workerA.getHired()),
                        parser.parse(workerA.getFired()),
                        workerA.getSalary(),
                        currencyConverter.convert(Currency.RUB, workerA.getSalary(), Currency.USD),
                        currencyConverter.convert(Currency.RUB, workerA.getSalary(), Currency.EUR)
                ))
                .append(System.lineSeparator())
                .append(String.format("Petr %s %s %s %s %s",
                        parser.parse(workerB.getHired()),
                        parser.parse(workerB.getFired()),
                        workerB.getSalary(),
                        currencyConverter.convert(Currency.RUB, workerB.getSalary(), Currency.USD),
                        currencyConverter.convert(Currency.RUB, workerB.getSalary(), Currency.EUR)
                ))
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }
}