package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class SalaryDescendingReport implements Report {
    private final Store store;
    private final CurrencyConverter currencyConverter;
    private final DateTimeParser dateTimeParser;

    public SalaryDescendingReport(Store store, CurrencyConverter currencyConverter, DateTimeParser dateTimeParser) {
        this.store = store;
        this.currencyConverter = currencyConverter;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary (RUB); Salary (USD); Salary (EUR)")
                .append(System.lineSeparator());

        List<Employee> employees = store.findBy(filter);
        Collections.sort(employees, Comparator.comparingDouble(Employee::getSalary).reversed());

        for (Employee employee : employees) {
            String hired = dateTimeParser.parse(employee.getHired());
            String fired = dateTimeParser.parse(employee.getFired());
            double salaryRub = employee.getSalary();
            double salaryUsd = currencyConverter.convert(Currency.RUB, salaryRub, Currency.USD);
            double salaryEur = currencyConverter.convert(Currency.RUB, salaryRub, Currency.EUR);

            text.append(employee.getName()).append(" ")
                    .append(hired).append(" ")
                    .append(fired).append(" ")
                    .append(salaryRub).append(" ")
                    .append(salaryUsd).append(" ")
                    .append(salaryEur)
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
