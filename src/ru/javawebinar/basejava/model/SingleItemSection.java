package ru.javawebinar.basejava.model;

public class SingleItemSection extends AbstractSection{

    String item;

    public SingleItemSection(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}
