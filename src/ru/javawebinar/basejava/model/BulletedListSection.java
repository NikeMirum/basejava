package ru.javawebinar.basejava.model;

import java.util.List;

public class BulletedListSection extends AbstractSection {

    private List<String> skills;

    public BulletedListSection(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "MultipleItemSection{" +
                "items=" + skills +
                '}';
    }
}