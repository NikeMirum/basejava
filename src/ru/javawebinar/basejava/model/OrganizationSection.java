package ru.javawebinar.basejava.model;

import java.util.List;

public class OrganizationSection extends AbstractSection {

    private List<Organization> organizations;

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "items=" + organizations +
                '}';
    }
}