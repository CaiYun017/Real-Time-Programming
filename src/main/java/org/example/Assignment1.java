package org.example;

import java.io.*;
import java.util.Arrays;

public class Assignment1 {
    private static int javaFileCount = 0;
    private static int issueFileCount = 0;

    public static void main(String[] args) {
        String folderPath = "C:\\Users\\ACER\\IdeaProjects\\Real-TimeProject";
        File folder = new File(folderPath);

        //System.out.println("Scanning folder: " + folderPath);
        //System.out.println("Folder exists? " + folder.exists());
        //System.out.println("Is directory? " + folder.isDirectory());

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files found in the folder.");
            return;
        }

        //System.out.println("Files in folder:");
        //Arrays.stream(files).forEach(file -> System.out.println(file.getName()));

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
            //System.out.println("Scanning: " + file.getAbsolutePath()); // 调试日志
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    countFiles(subFiles, fileExtension, checkIssue);
                } else {
                    //System.out.println("Cannot access subfiles in: " + file.getAbsolutePath());
                }
            } else if (file.getName().toLowerCase().endsWith(fileExtension)) {
                //System.out.println("Found Java file: " + file.getName()); // 调试日志
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
                if (line.contains("Solved")) {  //line.contains("TODO") || line.contains("FIXME") ||
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName());
        }
        return false;
    }
}
