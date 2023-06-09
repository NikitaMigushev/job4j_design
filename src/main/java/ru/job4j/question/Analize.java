package ru.job4j.question;

import java.util.*;

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
        Map<Integer, User> prevMap = createMap(previous);
        Map<Integer, User> currMap = createMap(current);

        if (!prevMap.equals(currMap) || !currMap.equals(prevMap)) {
            Iterator<Map.Entry<Integer, User>> prevIterator = prevMap.entrySet().iterator();
            while (prevIterator.hasNext()) {
                Map.Entry<Integer, User> searchUser = prevIterator.next();
                if (currMap.get(searchUser.getKey()) != null && currMap.get(searchUser.getKey()).equals(searchUser.getValue())) {
                    currMap.remove(searchUser.getKey());
                } else if (currMap.get(searchUser.getKey()) == null) {
                    info.setDeleted(info.getDeleted() + 1);
                } else if (!currMap.get(searchUser.getKey()).equals(searchUser.getValue())) {
                    info.setChanged(info.getChanged() + 1);
                    currMap.remove(searchUser.getKey());
                }
            }
            Iterator<Map.Entry<Integer, User>> currIterator = currMap.entrySet().iterator();
            while (currIterator.hasNext()) {
                info.setAdded(info.getAdded() + 1);
                currIterator.next();
            }
        }
        return info;
    }

    private static Map<Integer, User> createMap(Set<User> userSet) {
        Map<Integer, User> map = new HashMap<>();
        for (User user : userSet) {
            map.put(user.getId(), user);
        }
        return map;
    }
}
