package ru.javawebinar.basejava.model;

import java.util.Objects;

public class SimpleLineSection extends AbstractSection {

    private String text;

    public SimpleLineSection(String text) {
        Objects.requireNonNull(text, "content must not be null");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "SingleItemSection{" +
                "item='" + text + '\'' +
                '}';
    }
}