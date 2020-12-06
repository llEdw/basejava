package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class OrganizationDescription implements Serializable {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;
    private static final long serialVersionUID = 1L;

    public OrganizationDescription(LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(description, "description must not be null");
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
        return Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, title, description);
    }

    @Override
    public String toString() {
        DateTimeFormatter dF = DateTimeFormatter.ofPattern("MM/yy");
        if (title == null) {
            return "\b" + dF.format(startDate) + " - " + dF.format(endDate) + "\n" + description;
        } else {
            return "\b" + dF.format(startDate) + " - " + dF.format(endDate) + "\n" + title + "\n" + description;
        }
    }
}
