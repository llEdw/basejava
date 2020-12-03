package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;


public class Organization {
    private final List<OrganizationDescription> organizationDescriptions;
    private final String name;
    private final String url;

    public Organization(String name, String url, List<OrganizationDescription> organizationDescriptions) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(organizationDescriptions, "organizationDescriptions must not be null");
        this.organizationDescriptions = organizationDescriptions;
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(organizationDescriptions, that.organizationDescriptions) &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationDescriptions, name, url);
    }

    @Override
    public String toString() {
        return "\b\n\u2022" + name + "\n" + organizationDescriptions;
    }
}
