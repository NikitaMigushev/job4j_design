package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

public class UserHashEx {
    private String name;
    private int children;
    private Calendar birthday;

    public UserHashEx(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserHashEx user = (UserHashEx) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    private int hashCode;

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = name.hashCode();
            result = 31 * result + Integer.hashCode(children);
            result = 31 * result + birthday.hashCode();
            hashCode = result;
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public int getHashCode() {
        return hashCode;
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, 1987);
        birthday.set(Calendar.MONTH, Calendar.OCTOBER);
        birthday.set(Calendar.DAY_OF_MONTH, 9);
        UserHashEx userA = new UserHashEx("Nikita", 0, birthday);
        UserHashEx userB = new UserHashEx("Nikita", 0, birthday);
        int userAHashCode = userA.hashCode();
        int userBHashCode = userB.hashCode();
        System.out.println("User A hashcode is " + userA.getHashCode());
        System.out.println("User B hashcode is " + userB.getHashCode());
    }
}
