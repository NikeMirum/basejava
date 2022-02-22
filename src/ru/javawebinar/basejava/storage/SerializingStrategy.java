package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SerializingStrategy {

    public Resume doRead(InputStream is) throws IOException;

    public void doWrite(Resume r, OutputStream os) throws IOException;

}
