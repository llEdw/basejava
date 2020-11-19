package com.urise.webapp;

import java.io.File;

public class FileNames {
    public static void main(String[] args) {
        File projectName = new File("C:\\Users\\Эдуард\\IdeaProjects\\basejava");
        printFiles(projectName);
    }

    public static void printFiles(File projectName) {
        for (File fileName : projectName.listFiles()) {
            if (fileName.isDirectory()) {
                if (!fileName.equals(new File("C:\\Users\\Эдуард\\IdeaProjects\\basejava\\.git"))) {
                    printFiles(fileName);
                }
            } else {
                System.out.println(fileName.getName());
            }
        }
    }
}

