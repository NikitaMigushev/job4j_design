package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class SalaryConverterReport implements Report {
    private final Store store;
    private final CurrencyConverter currencyConverter;
    private final DateTimeParser<Calendar> dateTimeParser;

    public SalaryConverterReport(Store store, CurrencyConverter currencyConverter, DateTimeParser dateTimeParser) {
        this.store = store;
        this.currencyConverter = currencyConverter;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary (RUB); Salary (USD); Salary (EUR)")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            double salaryRub = employee.getSalary();
            double salaryUsd = currencyConverter.convert(Currency.RUB, salaryRub, Currency.USD);
            double salaryEur = currencyConverter.convert(Currency.RUB, salaryRub, Currency.EUR);

            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(salaryRub).append(" ")
                    .append(salaryUsd).append(" ")
                    .append(salaryEur)
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
