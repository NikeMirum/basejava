package ru.javawebinar.basejava.model;

public class SimpleLineSection extends AbstractSection {

    private String text;

    public SimpleLineSection(String text) {
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