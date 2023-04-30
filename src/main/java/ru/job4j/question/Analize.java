package ru.job4j.question;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Analize {
    /**
     * Method compares two sets of users and finds differences: how many added users, edited
     * and deleted.
     *
     * @param previous - is an original set of Users
     * @param current  - is a new set of Users
     * @return Info object with information about differences.
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        if (!previous.containsAll(current) || !current.containsAll(previous)) {
            Set<User> prev = new HashSet(previous);
            Set<User> curr = new HashSet(current);
            prev.removeAll(current);
            curr.removeAll(previous);
            System.out.println("check");
            for (User user : prev) {
                Optional<User> searchUser = findById(user.getId(), curr);
                if (!searchUser.isEmpty()) {
                    info.setChanged(info.getChanged() + 1);
                    curr.remove(searchUser.get());
                } else {
                    info.setDeleted(info.getDeleted() + 1);
                }
            }
            for (User user : curr) {
                info.setAdded(info.getAdded() + 1);
            }
        }
        return info;
    }

    private static Optional<User> findById(int id, Set<User> userSet) {
        Optional<User> rsl = Optional.empty();
        for (User user : userSet) {
            if (user.getId() == id) {
                rsl = Optional.of(user);
                break;
            }
        }
        return rsl;
    }
}
