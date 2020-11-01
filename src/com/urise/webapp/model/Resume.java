package com.urise.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume {

    private final String uuid;

    private final String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public enum PersonalObjectiveSection {
        PERSONAL("Личные качества"),
        OBJECTIVE("Позиция");

        private String title;
        private String description;

        PersonalObjectiveSection(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum AchievementQualificationsSection {
        ACHIEVEMENT("Достижения"),
        QUALIFICATIONS("Квалификация");

        private String title;
        private List<String> list = new ArrayList<>();

        AchievementQualificationsSection(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void addList(String description) {
            list.add(description);
        }

        public void removeList(String description) {
            list.remove(description);
        }

        public List<String> getList() {
            return list;
        }
    }

    public enum ExperienceEducationSection {
        EXPERIENCE("Опыт работы"),
        EDUCATION("Образование");

        private String title;
        private List<Organisation> list = new ArrayList<>();
        private Organisation a;

        ExperienceEducationSection(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void addOrganisation(String name) {
            a = new Organisation(name);
            list.add(a);
        }

        public void addMap(String date, String description) {
            a.map.put(date, description);
        }

        public void deleteMap(String date) {
            a.map.remove(date);
        }

        public List<Organisation> getList() {
            return list;
        }

        private class Organisation {
            private String name;
            private Map<String, String> map = new HashMap<>();

            public Organisation(String name) {
                this.name = name;
            }

            public String toString() {
                return name + map + "\n";
            }
        }
    }

    public enum ContactSection {
        CONTACT;

        private Map<String, String> map = new HashMap<>();

        public void addContact(String key, String value) {
            map.put(key, value);
        }

        public void deleteContact(String key) {
            map.remove(key);
        }

        public Map<String, String> getContact() {
            return map;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }
}
