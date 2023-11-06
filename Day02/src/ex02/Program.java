package ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Program {

    private static Path currentPath;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java FileManager --current-folder=<folder>");
            return;
        }

        String currentFolder = parseArgument(args[0]);
        if (currentFolder == null) {
            System.out.println("Invalid argument: " + args[0]);
            return;
        }

        currentPath = Paths.get(currentFolder);

        System.out.println(currentPath.toAbsolutePath());

        while (true) {
            System.out.print("> ");
            String command = System.console().readLine();
            if (command.equals("exit")) {
                break;
            }
            executeCommand(command);
        }
    }

    private static void executeCommand(String command) {
        String[] tokens = command.split("\\s+");
        if (tokens.length == 0) {
            return;
        }
        switch (tokens[0]) {
            case "ls":
                listFiles();
                break;
            case "cd":
                if (tokens.length > 1) {
                    changeDirectory(tokens[1]);
                }
                break;
            case "mv":
                if (tokens.length > 2) {
                    moveFile(tokens[1], tokens[2]);
                }
                break;
            default:
                System.out.println("Unknown command: " + tokens[0]);
        }
    }

    private static void listFiles() {
        File[] files = currentPath.toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.printf("%s %d KB\n", file.getName(), getFolderSize(file) / 1024);
                } else {
                    System.out.printf("%s %d KB\n", file.getName(), file.length() / 1024);
                }
            }
        }
    }

    private static void changeDirectory(String folderName) {
        Path newPath = currentPath.resolve(folderName);
        if (Files.isDirectory(newPath)) {
            currentPath = newPath;
            System.out.println(currentPath.toAbsolutePath());
        } else {
            System.out.println("Folder not found: " + folderName);
        }
    }

    private static void moveFile(String what, String where) {
        Path source = currentPath.resolve(what);
        Path target = currentPath.resolve(where);
        if (Files.exists(source)) {
            if (Files.isDirectory(target)) {
                target = target.resolve(source.getFileName());
            }
            try {
                Files.move(source, target);
            } catch (IOException e) {
                System.out.println("Error moving file: " + e.getMessage());
            }
        } else {
            System.out.println("File not found: " + what);
        }
    }

    private static long getFolderSize(File folder) {
        long size = 0;
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    size += getFolderSize(file);
                }
            }
        }
        return size;
    }

    private static String parseArgument(String arg) {
        if (arg.startsWith("--current-folder=")) {
            return arg.substring("--current-folder=".length());
        }
        return null;
    }
}