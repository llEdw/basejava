package com.urise.webapp;

import java.io.File;
import java.util.Objects;

public class FileNames {
    private static int tabulationCount = -1;

    public static void main(String[] args) {
        File projectName = new File(".");
        printFiles(projectName);
    }

    public static void printFiles(File projectName) {
        tabulationCount++;
        StringBuilder sb = new StringBuilder();
        for (File fileName : Objects.requireNonNull(projectName.listFiles(), "projectName.listFiles() must not be null")) {
            if (fileName.isDirectory()) {
                if (!fileName.equals(new File(".\\.git"))) {
                    for (int i = 0; i < 2 * tabulationCount; i++) {
                        sb.append("\t");
                    }
                    sb.append("Directory : ");
                    sb.append(fileName.getName());
                    System.out.println(sb);
                    sb.delete(0, sb.length());
                    printFiles(fileName);
                }
            } else {
                for (int i = 0; i < 2 * tabulationCount; i++) {
                    sb.append("\t");
                }
                sb.append("File      : ");
                sb.append(fileName.getName());
                System.out.println(sb);
                sb.delete(0, sb.length());
            }
        }
        tabulationCount--;
    }
}

