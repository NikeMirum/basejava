package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeWithException(contacts.entrySet(), dos, contactEntry -> {
                dos.writeUTF(contactEntry.getKey().name());
                dos.writeUTF(contactEntry.getValue());
            });
            Map<SectionType, AbstractSection> sections = r.getSections();
            writeWithException(sections.entrySet(), dos, sectionEntry -> {
                SectionType type = sectionEntry.getKey();
                AbstractSection section = sectionEntry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        List<String> items = ((ListSection) section).getItems();
                        writeWithException(items, dos, dos::writeUTF);
                        break;
                    }
                    case EXPERIENCE:
                    case EDUCATION: {
                        List<Organization> organizations = ((OrganizationSection) section).getOrganizations();
                        writeWithException(organizations, dos, organization -> {
                            Link homePage = organization.getHomePage();
                            String url = homePage.getUrl();
                            dos.writeUTF(homePage.getName());
                            dos.writeUTF((url == null) ? " " : url);
                            List<Organization.Position> positions = organization.getPositions();
                            writeWithException(positions, dos, position -> {
                                writeLocalDate(dos, position.getStartDate());
                                writeLocalDate(dos, position.getEndDate());
                                dos.writeUTF(position.getTitle());
                                String description = position.getDescription();
                                dos.writeUTF((description == null) ? " " : description);
                            });
                        });
                        break;
                    }
                }
            });
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
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE: resume.addSection(type, new TextSection(dis.readUTF()));
                    break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        int listSectionSize = dis.readInt();
                        List<String> list = new ArrayList<>(listSectionSize);
                        for (int j = 0; j < listSectionSize; j++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(type, new ListSection(list));
                    }
                    break;
                    case EXPERIENCE:
                    case EDUCATION: {
                        int organizationsSize = dis.readInt();
                        List<Organization> organizations = new ArrayList<>(organizationsSize);
                        for (int j = 0; j < organizationsSize; j++) {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            Link link = new Link(name, (url.equals(" ")) ? null : url);
                            List<Organization.Position> positions = new ArrayList<>();
                            int positionSize = dis.readInt();
                            for (int k = 0; k < positionSize; k++) {
                                LocalDate startDate = readLocalDate(dis);
                                LocalDate endDate = readLocalDate(dis);
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
                    break;
                }
            }
            return resume;
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }


    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, ElementWriter<T> er)
            throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            er.write(t);
        }
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }
}