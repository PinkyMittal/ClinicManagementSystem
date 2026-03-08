package com.airtribe.meditrack.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple in-memory store used for CRUD operations.
 */
public class DataStore<T> {
    private final Map<String, T> items = new HashMap<>();

    public void add(String id, T item) {
        items.put(id, item);
    }

    public T get(String id) {
        return items.get(id);
    }

    public boolean remove(String id) {
        return items.remove(id) != null;
    }

    public List<T> list() {
        return new ArrayList<>(items.values());
    }

    public boolean containsId(String id) {
        return items.containsKey(id);
    }

    public int size() {
        return items.size();
    }
}
