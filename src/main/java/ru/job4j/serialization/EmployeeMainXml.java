package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class EmployeeMainXml {
    public static void main(String[] args) throws Exception {
        String[] skills = {"Java", "SQL", "JS", "Design Pattern",
                "Microservices", "Rest API", "Hibername", "Spring",
                "Kafka", "Maven", "Gradle", "Docker", "Git"};
        Employee employee = new Employee("John Doe", false,
                24, new Contact("111-11-11"), skills);
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employee, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Employee result = (Employee) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
