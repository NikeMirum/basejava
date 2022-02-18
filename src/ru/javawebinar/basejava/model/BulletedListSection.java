package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class BulletedListSection extends AbstractSection {

    private List<String> skills;

    public BulletedListSection(List<String> skills) {
        Objects.requireNonNull(skills, "skills must not be null");
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