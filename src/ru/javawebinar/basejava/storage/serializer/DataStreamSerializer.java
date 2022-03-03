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
            readWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readWithException(dis, () -> {
                SectionType type = SectionType.valueOf(dis.readUTF());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(type, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        resume.addSection(type, new ListSection(readListWithException(dis, dis::readUTF)));

                    }
                    break;
                    case EXPERIENCE:
                    case EDUCATION: {
                        resume.addSection(type, new OrganizationSection(
                                readListWithException(dis, () -> new Organization(readLink(dis),
                                        readListWithException(dis, () ->
                                                readPosition(dis))
                                ))));
                    }
                    break;
                }
            });
            return resume;
        }
    }

    private Link readLink(DataInputStream dis) throws IOException {
        String name = dis.readUTF();
        String url = dis.readUTF();
        return new Link(name, (url.equals(" ")) ? null : url);
    }

    private Organization.Position readPosition(DataInputStream dis) throws IOException {
        LocalDate startDate = readLocalDate(dis);
        LocalDate endDate = readLocalDate(dis);
        String title = dis.readUTF();
        String description = dis.readUTF();
        return new Organization.Position(startDate, endDate, title, description.equals(" ") ? null : description);
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private void readWithException(DataInputStream dis, ElementReader er)
            throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            er.read();
        }
    }

    private <T> List<T> readListWithException(DataInputStream dis, ListReader<T> lr) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(lr.read());
        }
        return list;
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, ElementWriter<T> ew)
            throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            ew.write(t);
        }
    }

    private interface ElementReader {
        void read() throws IOException;
    }

    private interface ListReader<T> {
        T read() throws IOException;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }
}