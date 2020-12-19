package com.urise.webapp.storage.serialization;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
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
                dos.writeUTF(entry.getKey().name());
                switch (entry.getKey()) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(entry.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int sizeOfItems = ((ListSection) entry.getValue()).getItems().size();
                        dos.writeInt(sizeOfItems);
                        for (String item : ((ListSection) entry.getValue()).getItems()) {
                            dos.writeUTF(item);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int sizeOfOrganization = ((OrganizationSection) entry.getValue()).getOrganizations().size();
                        dos.writeInt(sizeOfOrganization);
                        for (Organization organization : ((OrganizationSection) entry.getValue()).getOrganizations()) {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl() + "");
                            dos.writeInt(organization.getPositions().size());
                            for (Organization.Position position : organization.getPositions()) {
                                dos.writeUTF(position.getDescription() + "");
                                dos.writeUTF(position.getTitle());
                                writeDate(dos, position.getStartDate());
                                writeDate(dos, position.getEndDate());
                            }
                        }
                        break;
                }
            }
        }
    }

    private void writeDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeUTF(date.getMonth().name());
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
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        String contents = dis.readUTF();
                        resume.addSection(sectionType, new TextSection(contents));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> items = new ArrayList<>();
                        int sizeOfItems = dis.readInt();
                        for (int j = 0; j < sizeOfItems; j++) {
                            items.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(items));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = new ArrayList<>();
                        int sizeOfOrganization = dis.readInt();
                        for (int j = 0; j < sizeOfOrganization; j++) {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            if (url.equals("null")) {
                                url = null;
                            }
                            ArrayList<Organization.Position> positions = new ArrayList<>();
                            int sizeOfPositions = dis.readInt();
                            for (int k = 0; k < sizeOfPositions; k++) {
                                String description = dis.readUTF();
                                if (description.equals("null")) {
                                    description = null;
                                }
                                String title = dis.readUTF();
                                LocalDate startDate = readDate(dis);
                                LocalDate endDate = readDate(dis);
                                positions.add(new Organization.Position(startDate, endDate, title, description));
                            }
                            organizations.add(new Organization(name, url, positions));
                        }
                        resume.addSection(sectionType, new OrganizationSection(organizations));
                        break;
                }
            }
            return resume;
        }
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        int year = dis.readInt();
        Month month = Month.valueOf(dis.readUTF());
        return LocalDate.of(year, month, 1);
    }
}
