package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;


public class Organization {
    private final List<OrganizationDescription> organizationDescriptions;
    private final String name;

    public Organization(String name, List<OrganizationDescription> organizationDescriptions) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(organizationDescriptions, "organizationDescriptions must not be null");
        this.organizationDescriptions = organizationDescriptions;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return organizationDescriptions.equals(that.organizationDescriptions) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + organizationDescriptions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\b\n\u2022" + name + "\n" + organizationDescriptions;
    }
}
