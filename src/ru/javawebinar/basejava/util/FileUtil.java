package ru.javawebinar.basejava.util;

import java.io.File;

public class FileUtil {

    public static void handleFile(File file, String shift) {
        System.out.print(shift + " ");
        System.out.println(file.getName() + " - " + file.length() + " bytes");
    }

    public static void handleFolder(File file, String shift) {
        System.out.print(shift);
        System.out.println(file.getName());
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    handleFolder(file1, shift + " ");
                } else if (file1.isFile()) {
                    handleFile(file1, shift);
                } else {
                    System.out.println(file1.getName() + "Undefined structure element");
                }
            }
        }
    }
}
