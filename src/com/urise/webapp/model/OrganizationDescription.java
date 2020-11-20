package com.urise.webapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class OrganizationDescription {
    private final String url;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public OrganizationDescription(String url, LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        this.url = url;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationDescription that = (OrganizationDescription) o;

        if (!url.equals(that.url)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!title.equals(that.title)) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
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
            return "\b" + dF.format(startDate) + " - " + dF.format(endDate) + "\n" + description;
        } else {
            return "\b" + dF.format(startDate) + " - " + dF.format(endDate) + "\n" + title + "\n" + description;
        }
    }
}
