package com.urise.webapp.storage.serialization;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerialization implements Serialization {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeUTF((entry.getValue()).getClass().getSimpleName());
                if (entry.getValue() instanceof TextSection) {
                    dos.writeUTF(entry.getKey().name());
                    dos.writeUTF(entry.getValue().toString());
                } else if (entry.getValue() instanceof ListSection) {
                    int sizeOfItems = ((ListSection) entry.getValue()).getItems().size();
                    dos.writeInt(sizeOfItems);
                    dos.writeUTF(entry.getKey().name());
                    for (String item : ((ListSection) entry.getValue()).getItems()) {
                        dos.writeUTF(item);
                    }
                } else {
                    int sizeOfOrganization = ((OrganizationSection) entry.getValue()).getOrganizations().size();
                    dos.writeInt(sizeOfOrganization);
                    dos.writeUTF(entry.getKey().name());
                    for (Organization organization : ((OrganizationSection) entry.getValue()).getOrganizations()) {
                        dos.writeUTF(organization.getHomePage().getName());
                        dos.writeUTF(organization.getHomePage().getUrl() + "");
                        dos.writeInt(organization.getPositions().size());
                        for (Organization.Position position : organization.getPositions()) {
                            dos.writeUTF(position.getDescription() + "");
                            dos.writeUTF(position.getTitle());
                            dos.writeInt(position.getStartDate().getYear());
                            dos.writeUTF(position.getStartDate().getMonth().name());
                            dos.writeInt(position.getEndDate().getYear());
                            dos.writeUTF(position.getEndDate().getMonth().name());
                        }
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int sizeOfContacts = dis.readInt();
            for (int i = 0; i < sizeOfContacts; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sizeOfSection = dis.readInt();
            for (int i = 0; i < sizeOfSection; i++) {
                String className = dis.readUTF();
                if (className.equals("TextSection")) {
                    SectionType sectionType = SectionType.valueOf(dis.readUTF());
                    String contents = dis.readUTF();
                    resume.addSection(sectionType, new TextSection(contents));
                } else if (className.equals("ListSection")) {
                    List<String> items = new ArrayList<>();
                    int sizeOfItems = dis.readInt();
                    SectionType sectionType = SectionType.valueOf(dis.readUTF());
                    for (int j = 0; j < sizeOfItems; j++) {
                        items.add(dis.readUTF());
                    }
                    resume.addSection(sectionType, new ListSection(items));
                } else {
                    List<Organization> organizations = new ArrayList<>();
                    int sizeOfOrganization = dis.readInt();
                    SectionType sectionType = SectionType.valueOf(dis.readUTF());
                    for (int j = 0; j < sizeOfOrganization; j++) {
                        String name = dis.readUTF();
                        String url = dis.readUTF();
                        if (url.equals("null")) url = null;
                        int sizeOfPositions = dis.readInt();
                        List<Organization.Position> positions = new ArrayList<>();
                        for (int k = 0; k < sizeOfPositions; k++) {
                            String description = dis.readUTF();
                            if (description.equals("null")) description = null;
                            String title = dis.readUTF();
                            int startYear = dis.readInt();
                            Month startMonth = Month.valueOf(dis.readUTF());
                            int endYear = dis.readInt();
                            Month endMonth = Month.valueOf(dis.readUTF());
                            positions.add(new Organization.Position(startYear, startMonth, endYear, endMonth, title, description));
                        }
                        organizations.add(new Organization(name, url, positions));
                    }
                    resume.addSection(sectionType, new OrganizationSection(organizations));
                }
            }
            return resume;
        }
    }
}
