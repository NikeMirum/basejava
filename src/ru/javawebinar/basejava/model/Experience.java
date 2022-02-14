package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience {

    private String title;
    private String description;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String organization;
    private String hyperLink;

    public Experience(String title, String description, LocalDate beginDate, LocalDate endDate, String organization, String hyperLink) {
        this.title = title;
        this.description = description;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.organization = organization;
        this.hyperLink = hyperLink;
    }

    public Experience(String title, String description, LocalDate beginDate, LocalDate endDate, String organization) {
        this.title = title;
        this.description = description;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.organization = organization;
    }

    public Experience(String title, LocalDate beginDate, LocalDate endDate, String organization, String hyperLink) {
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.organization = organization;
        this.hyperLink = hyperLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return title.equals(that.title) && Objects.equals(description, that.description) && beginDate.equals(that.beginDate) && Objects.equals(endDate, that.endDate) && organization.equals(that.organization) && Objects.equals(hyperLink, that.hyperLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, beginDate, endDate, organization, hyperLink);
    }

    @Override
    public String toString() {
        return "Experience{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", organization='" + organization + '\'' +
                ", hyperLink='" + hyperLink + '\'' +
                '}';
    }
}
