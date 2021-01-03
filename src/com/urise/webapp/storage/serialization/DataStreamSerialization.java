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
            writeMapWithException(resume.getContacts(), dos, (k, v) -> {
                dos.writeUTF(k.name());
                dos.writeUTF(v);
            });
            writeMapWithException(resume.getSections(), dos, (k, v) -> {
                dos.writeUTF(k.name());
                switch (k) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(v.toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeListWithException(((ListSection) v).getItems(), dos, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeListWithException(((OrganizationSection) v).getOrganizations(), dos, t -> {
                            dos.writeUTF(t.getHomePage().getName());
                            String url = t.getHomePage().getUrl();
                            dos.writeUTF(url == null ? "" : url);
                            writeListWithException(t.getPositions(), dos, t2 -> {
                                String descripion = t2.getDescription();
                                dos.writeUTF(descripion == null ? "" : descripion);
                                dos.writeUTF(t2.getTitle());
                                writeDate(dos, t2.getStartDate());
                                writeDate(dos, t2.getEndDate());
                            });
                        });
                        break;
                }
            });
        }
    }

    private <K, V> void writeMapWithException(Map<K, V> map, DataOutputStream dos, MapWriter<K, V> action) throws IOException {
        dos.writeInt(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            action.accept(entry.getKey(), entry.getValue());
        }
    }

    private <T> void writeListWithException(List<T> list, DataOutputStream dos, ListWriter<T> action) throws IOException {
        dos.writeInt(list.size());
        for (T item : list) {
            action.accept(item);
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
            readCollectionWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readCollectionWithException(dis, () -> {
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
                        readCollectionWithException(dis, () -> items.add(dis.readUTF()));
                        resume.addSection(sectionType, new ListSection(items));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = new ArrayList<>();
                        readCollectionWithException(dis, () -> {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            if (url.equals("")) {
                                url = null;
                            }
                            ArrayList<Organization.Position> positions = new ArrayList<>();
                            readCollectionWithException(dis, () -> {
                                String description = dis.readUTF();
                                if (description.equals("")) {
                                    description = null;
                                }
                                String title = dis.readUTF();
                                LocalDate startDate = readDate(dis);
                                LocalDate endDate = readDate(dis);
                                positions.add(new Organization.Position(startDate, endDate, title, description));
                            });
                            organizations.add(new Organization(name, url, positions));
                        });
                        resume.addSection(sectionType, new OrganizationSection(organizations));
                        break;
                }
            });
            return resume;
        }
    }

    private void readCollectionWithException(DataInputStream dis, CollectionReader action) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            action.accept();
        }
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        int year = dis.readInt();
        Month month = Month.valueOf(dis.readUTF());
        return LocalDate.of(year, month, 1);
    }
}

