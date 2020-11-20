package com.urise.webapp;

import java.io.File;
import java.util.Objects;

public class FileNames {
    public static void main(String[] args) {
        File projectName = new File(".");
        printFiles(projectName);
    }

    public static void printFiles(File projectName) {
        for (File fileName : Objects.requireNonNull(projectName.listFiles(), "projectName.listFiles() must not be null")) {
            if (fileName.isDirectory()) {
                if (!fileName.equals(new File(".\\.git"))) {
                    printFiles(fileName);
                }
            } else {
                System.out.println(fileName.getName());
            }
        }
    }
}

