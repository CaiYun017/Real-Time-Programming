package org.example;

import java.io.*;
import java.util.Arrays;

public class Assignment1 {
    private static int javaFileCount = 0;
    private static int issueFileCount = 0;

    public static void main(String[] args) {
        String folderPath = "C:\\Users\\ACER\\IdeaProjects\\Real-TimeProject";
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files found in the folder.");
            return;
        }


        Thread countJavaThread = new Thread(() -> countFiles(files, ".java", false));
        Thread countIssueThread = new Thread(() -> countFiles(files, ".java", true));

        countJavaThread.start();
        countIssueThread.start();

        try {
            countJavaThread.join();
            countIssueThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }

        System.out.println("Number of Java Files = " + javaFileCount);
        System.out.println("Number of Issues = " + issueFileCount);
    }

    private static void countFiles(File[] files, String fileExtension, boolean checkIssue) {
        for (File file : files) {
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    countFiles(subFiles, fileExtension, checkIssue);
                } else {
                }
            } else if (file.getName().toLowerCase().endsWith(fileExtension)) {
                if (checkIssue) {
                    if (isIssueFile(file)) {
                        synchronized (Assignment1.class) {
                            issueFileCount++;
                        }
                    }
                } else {
                    synchronized (Assignment1.class) {
                        javaFileCount++;
                    }
                }
            }
        }
    }

    private static boolean isIssueFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Solved")) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName());
        }
        return false;
    }
}
