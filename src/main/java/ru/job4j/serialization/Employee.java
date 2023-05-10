package ru.job4j.serialization;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean employed;
    @XmlAttribute
    private int age;
    private Contact contact;
    @XmlElementWrapper(name = "skills")
    @XmlElement(name = "skill")
    private String[] skills;

    public Employee() { }

    public Employee(String name, boolean employed, int age, Contact contact, String[] skills) {
        this.name = name;
        this.employed = employed;
        this.age = age;
        this.contact = contact;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", employed=" + employed
                + ", age=" + age
                + ", contact=" + contact
                + ", skills=" + Arrays.toString(skills)
                + '}';
    }
}


