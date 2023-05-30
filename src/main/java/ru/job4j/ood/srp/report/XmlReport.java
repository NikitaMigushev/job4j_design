package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class XmlReport implements Report {
    private final Store store;
    private final DateTimeParser dateTimeParser;

    public XmlReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<employees>\n");
        for (Employee employee : store.findBy(filter)) {
            xml.append("\t<employee>\n")
                    .append("\t\t<name>").append(employee.getName()).append("</name>\n")
                    .append("\t\t<hired>").append(dateTimeParser.parse(employee.getHired())).append("</hired>\n")
                    .append("\t\t<fired>").append(dateTimeParser.parse(employee.getFired())).append("</fired>\n")
                    .append("\t\t<salary>").append(employee.getSalary()).append("</salary>\n")
                    .append("\t</employee>\n");
        }
        xml.append("</employees>");
        return xml.toString();
    }

    public static void main(String[] args) {
        Store store = new MemStore();
        DateTimeParser dateTimeParser = new ReportDateTimeParser();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("John Doe", now, now, 5000.0));
        store.add(new Employee("Jane Smith", now, now, 6000.0));
        XmlReport xmlReport = new XmlReport(store, dateTimeParser);
        String xml = xmlReport.generate(employee -> true);
        System.out.println(xml);
    }
}
