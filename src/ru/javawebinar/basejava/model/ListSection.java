package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * gkislin
 * 14.07.2016
 */
public class ListSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    private List<String> items;

    public static final ListSection EMPTY = new ListSection("");

    public ListSection() {
    }

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    public ListSection(List<String> items) {
        Objects.requireNonNull(items, "items must not be null");
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(items.size() != 0 ? items.get(0) : "");
        for (int i = 1; i < items.size(); i++) {
            sb.append("\n").append(items.get(i));
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return items.equals(that.items);

    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}