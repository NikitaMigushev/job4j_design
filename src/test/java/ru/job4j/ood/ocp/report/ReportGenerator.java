package ru.job4j.ood.ocp.report;

import ru.job4j.ood.srp.model.Employee;

import java.util.List;

public class ReportGenerator {
    public void generateReport(EmployeeRepository employeeRepository) {
        List<Employee> employees = employeeRepository.getEmployees();
    }
}
