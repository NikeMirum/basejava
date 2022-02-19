package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;

public class Organization {

    private List<Experience> experiences;
    private String name;
    private String hyperLink;

    public Organization(String name, String hyperLink, Experience... experiences) {
        this.name = name;
        this.hyperLink = hyperLink;
        this.experiences = Arrays.asList(experiences);
    }

}