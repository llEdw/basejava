package com.urise.webapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * gkislin
 * 19.07.2016
 */
public class Organization {
    private final Link homePage;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        this.name = name;
        this.homePage = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!title.equals(that.title)) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    DateTimeFormatter dF = DateTimeFormatter.ofPattern("MM/yy");

    @Override
    public String toString() {
        if (title == null) {
            if (name != null) {
                return "\b\n\u2022" + name + "\n" + dF.format(startDate) + " - " + dF.format(endDate) + "\n" + description;
            } else {
                return "\n" + dF.format(startDate) + " - " + dF.format(endDate) + "\n" + description;
            }
        } else {
            if (name != null) {
                return "\b\n\u2022" + name + "\n" + dF.format(startDate) + " - " + dF.format(endDate) + "\n" + title + "\n" + description;
            } else {
                return "\n" + dF.format(startDate) + " - " + dF.format(endDate) + "\n" + title + "\n" + description;
            }
        }
    }
}