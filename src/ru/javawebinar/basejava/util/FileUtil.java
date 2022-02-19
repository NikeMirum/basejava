package ru.javawebinar.basejava.util;

import java.io.File;

public class FileUtil {
    private static int shiftNumbers = 0;

    public static void handleFile(File file) {
        for (int i = 0; i < shiftNumbers; i++) {
            System.out.print(" ");
        }
        System.out.println(file.getName() + " - " + file.length() + " bytes");
    }

    public static void handleFolder(File file) {
        for (int i = 0; i < shiftNumbers; i++) {
            System.out.print(" ");
        }
        System.out.println(file.getName());
        File[] files = file.listFiles();
        if (files!=null) {
            shiftNumbers++;
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    handleFolder(file1);
                } else if (file1.isFile()) {
                    handleFile(file1);
                } else {
                    System.out.println(file1.getName() + "Undefined structure element");
                }
            }
            shiftNumbers--;
        }
    }
}
