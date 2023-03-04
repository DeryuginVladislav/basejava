package ru.javawebinar.basejava.storage.serialization;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamStrategy implements SerializationStrategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            dos.writeInt(resume.getContacts().size());
            for (Map.Entry<ContactType, String> contact : resume.getContacts().entrySet()) {
                dos.writeUTF(contact.getKey().name());
                dos.writeUTF(contact.getValue());
            }
            dos.writeInt(resume.getSections().size());
            for (Map.Entry<SectionType, AbstractSection> section : resume.getSections().entrySet()) {
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
                        dos.writeInt(listSection.getStrings().size());
                        for (String string : listSection.getStrings()) {
                            dos.writeUTF(string);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = (OrganizationSection) section.getValue();
                        dos.writeInt(organizationSection.getOrganizations().size());
                        for (Organization organization : organizationSection.getOrganizations()) {
                            dos.writeUTF(organization.getTitle());
                            writeStringMayBeEmpty(organization.getWebsite(), dos);
                            dos.writeInt(organization.getPeriods().size());
                            for (Organization.Period period : organization.getPeriods()) {
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                writeStringMayBeEmpty(period.getDescription(), dos);
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());
            int contactSize = dis.readInt();
            for (int i = 0; i < contactSize; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int stringCount = dis.readInt();
                        List<String> strings = new ArrayList<>();
                        for (int j = 0; j < stringCount; j++) {
                            strings.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(strings));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int organizationCount = dis.readInt();
                        List<Organization> organizations = new ArrayList<>();
                        for (int j = 0; j < organizationCount; j++) {
                            String title = dis.readUTF();
                            String website = readStringMayBeEmpty(dis);
                            int periodCount = dis.readInt();
                            List<Organization.Period> periods = new ArrayList<>();
                            for (int k = 0; k < periodCount; k++) {
                                String titlePeriod = dis.readUTF();
                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                LocalDate endDate = LocalDate.parse(dis.readUTF());
                                String description = readStringMayBeEmpty(dis);
                                periods.add(new Organization.Period(titlePeriod, startDate, endDate, description));
                            }
                            organizations.add(new Organization(title, website, periods));
                        }
                        resume.addSection(sectionType, new OrganizationSection(organizations));
                        break;
                }
            }
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
}