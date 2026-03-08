package com.airtribe.meditrack.interfaces;

import java.util.List;

/**
 * Defines search operations.
 */
public interface Searchable<T> {
    List<T> searchById(String id);

    List<T> searchByName(String name);

    List<T> searchByAge(int age);

    default List<T> searchByKeyword(String keyword) {
        return searchByName(keyword);
    }
}
