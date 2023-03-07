package ru.javawebinar.basejava.storage.serialization;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamStrategy implements SerializationStrategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            writeWithException(resume.getContacts().entrySet(), dos, (contact) -> {
                dos.writeUTF(contact.getKey().name());
                dos.writeUTF(contact.getValue());
            });
            writeWithException(resume.getSections().entrySet(), dos, (section) -> {
                SectionType sectionType = section.getKey();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        TextSection textSection = (TextSection) section.getValue();
                        dos.writeUTF(textSection.getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = (ListSection) section.getValue();
                        writeWithException(listSection.getStrings(), dos, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = (OrganizationSection) section.getValue();
                        writeWithException(organizationSection.getOrganizations(), dos, (organization) -> {
                            dos.writeUTF(organization.getTitle());
                            writeStringMayBeEmpty(organization.getWebsite(), dos);
                            writeWithException(organization.getPeriods(), dos, (period) -> {
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                writeStringMayBeEmpty(period.getDescription(), dos);
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());
            readWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> strings = new ArrayList<>();
                        readWithException(dis, () -> strings.add(dis.readUTF()));
                        resume.addSection(sectionType, new ListSection(strings));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = new ArrayList<>();
                        readWithException(dis, () -> {
                            String title = dis.readUTF();
                            String website = readStringMayBeEmpty(dis);
                            List<Organization.Period> periods = new ArrayList<>();
                            readWithException(dis, () -> {
                                String titlePeriod = dis.readUTF();
                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                LocalDate endDate = LocalDate.parse(dis.readUTF());
                                String description = readStringMayBeEmpty(dis);
                                periods.add(new Organization.Period(titlePeriod, startDate, endDate, description));
                            });
                            organizations.add(new Organization(title, website, periods));
                        });
                        resume.addSection(sectionType, new OrganizationSection(organizations));
                        break;
                }
            });
            return resume;
        }
    }

    private void writeStringMayBeEmpty(String s, DataOutputStream dos) throws IOException {
        if (s != null) {
            dos.writeUTF(s);
        } else dos.writeUTF("null");
    }

    private String readStringMayBeEmpty(DataInputStream dis) throws IOException {
        String string = dis.readUTF();
        if (string.equals("null")) {
            string = null;
        }
        return string;
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writer.write(element);
        }
    }

    private interface Writer<T> {
        void write(T element) throws IOException;
    }

    private void readWithException(DataInputStream dis, Reader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    private interface Reader {
        void read() throws IOException;
    }
}