package ru.javawebinar.basejava.model;

import java.util.List;

public class MultipleItemSection extends AbstractSection {

    List<String> items;

    public MultipleItemSection(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "MultipleItemSection{" +
                "items=" + items +
                '}';
    }
}
