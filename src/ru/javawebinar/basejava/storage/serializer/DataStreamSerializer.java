package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, AbstractSection> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(type.name());
                if (type == SectionType.PERSONAL || type == SectionType.OBJECTIVE) {
                    dos.writeUTF(((TextSection) section).getContent());
                }
                if (type == SectionType.ACHIEVEMENT || type == SectionType.QUALIFICATIONS) {
                    List<String> items = ((ListSection) section).getItems();
                    dos.writeInt(items.size());
                    for (String item : items) {
                        dos.writeUTF(item);
                    }
                }
                if (type == SectionType.EXPERIENCE || type == SectionType.EDUCATION) {
                    List<Organization> organizations = ((OrganizationSection) section).getOrganizations();
                    dos.writeInt(organizations.size());
                    for (Organization organization : organizations) {
                        dos.writeUTF(organization.getHomePage().getName());
                        dos.writeUTF((organization.getHomePage().getUrl() == null) ?
                                " " : organization.getHomePage().getUrl());
                        List<Organization.Position> positions = organization.getPositions();
                        dos.writeInt(positions.size());
                        for (Organization.Position position : positions) {
                            dos.writeInt(position.getStartDate().getYear());
                            dos.writeInt(position.getStartDate().getMonth().getValue());
                            dos.writeInt(position.getEndDate().getYear());
                            dos.writeInt(position.getEndDate().getMonth().getValue());
                            dos.writeUTF(position.getTitle());
                            dos.writeUTF((position.getDescription() == null) ?
                                    " " : position.getDescription());
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
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                if (type == SectionType.PERSONAL || type == SectionType.OBJECTIVE) {
                    resume.addSection(type, new TextSection(dis.readUTF()));
                }
                if (type == SectionType.ACHIEVEMENT || type == SectionType.QUALIFICATIONS) {
                    int listSectionSize = dis.readInt();
                    List<String> list = new ArrayList<>(listSectionSize);
                    for (int j = 0; j < listSectionSize; j++) {
                        list.add(dis.readUTF());
                    }
                    resume.addSection(type, new ListSection(list));
                }
                if (type == SectionType.EXPERIENCE || type == SectionType.EDUCATION) {
                    int organizationsSize = dis.readInt();
                    List<Organization> organizations = new ArrayList<>(organizationsSize);
                    for (int j = 0; j < organizationsSize; j++) {
                        String name = dis.readUTF();
                        String url = dis.readUTF();
                        Link link = new Link(name, (url.equals(" ")) ? null : url);
                        List<Organization.Position> positions = new ArrayList<>();
                        int positionSize = dis.readInt();
                        for (int k = 0; k < positionSize; k++) {
                            LocalDate startDate = LocalDate.of(dis.readInt(), dis.readInt(), 1);
                            LocalDate endDate = LocalDate.of(dis.readInt(), dis.readInt(), 1);
                            String title = dis.readUTF();
                            String description = dis.readUTF();
                            Organization.Position position = new Organization.Position(startDate, endDate, title,
                                    description.equals(" ") ? null : description);
                            positions.add(position);
                        }
                        organizations.add(new Organization(link, positions));
                    }
                    resume.addSection(type, new OrganizationSection(organizations));
                }
            }
            return resume;
        }
    }
}