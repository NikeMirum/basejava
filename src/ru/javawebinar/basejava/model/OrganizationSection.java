package ru.javawebinar.basejava.model;

import java.util.List;

public class OrganizationSection extends AbstractSection {

    private List<Experience> items;

    public OrganizationSection(List<Experience> items) {
        this.items = items;
    }

    public List<Experience> getItems() {
        return items;
    }
}
