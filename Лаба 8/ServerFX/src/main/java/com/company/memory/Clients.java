package com.company.memory;

import com.company.utility.Log;

import java.util.HashMap;
import java.util.Map;

public class Clients {
    public static final int MAX_USERS_COUNT = 10;
    private final Log log = new Log();
    private final Map<String, Integer> users = new HashMap<>();

    public synchronized void add(String LOGIN, String PASS, int id) {
        synchronized (users) {
            users.put(LOGIN + PASS, id);
        }
    }

    public synchronized void remove(String LOGIN, String PASS) {
        synchronized (users) {
            users.remove(LOGIN + PASS);
        }
    }

    public synchronized int contains(String LOGIN, String PASS) {
        synchronized (users) {
            return users.getOrDefault(LOGIN + PASS, -1);
        }
    }

    public synchronized void disconnect(String LOGIN) {
        synchronized (users) {
            log.disconnect(LOGIN);
        }
    }
}
