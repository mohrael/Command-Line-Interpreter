import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CLI {
    private String currentDirectory = System.getProperty("user.dir");

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.start();
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        String command;
        while (true) {
            System.out.println("> ");
            command = scanner.nextLine();
            if (command.equals("exit")) break;
            execute(command);
        }
        scanner.close();

    }
    public void execute(String command) {
        if (command.isEmpty()) return;
        String[] parts = command.split(" ", 2);
        String cmd = parts[0];
        String args = parts.length > 1 ? parts[1] : "";
        switch (cmd) {
            case "pwd":
                System.out.println(pwd(args));
                break;
            case "cd":
                changeDirectory(args);
                break;
            case "ls":
                listFiles();
                break;
            case "touch":
                createFile(args);
                break;
            case "mkdir":
                createDirectory(args);
                break;
            case "help":
                showHelp();
                break;
            default:
                System.out.println( "Command not found!");
        }

    }

    private void changeDirectory(String path) {
        File newDirectory = new File(path);
        if (newDirectory.isDirectory()) {
            currentDirectory = newDirectory.getAbsolutePath();
            System.out.println("Current directory: " + currentDirectory);
        }
        else{
            System.out.println("Directory not found");
        }
    }

    private void listFiles() {
        File dir = new File(currentDirectory);
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                System.out.println(file);
            }
        }
        else {
            System.out.println("Directory is empty or not accessible");
        }

    }
    public String pwd(String args) {
        if (!args.isEmpty()) {
            return "Invalid Command!";
        }
        return System.getProperty("user.dir");
    }
    void createFile(String args) {
        if (args.isEmpty()) {
            System.out.println("File name required");
            return;
        }
        File file = new File(currentDirectory, args);
        try {
            if (file.createNewFile()) {
                System.out.println("File " + args + " created");
            }
            else {
                System.out.println("File " + args + " already exists");
            }
        }
        catch(Exception e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
    boolean createDirectory(String args) {
        if (args.isEmpty()) {
            System.out.println("Directory name required");
            return false;
        }
        File file = new File(currentDirectory, args);
        if(file.mkdir()){
            System.out.println("Directory " + args + " created");
            return true;
        }
        else {
            System.out.println("Directory " + args + " already exists or could not be created");
        return false;
        }


    }
    private void showHelp() {
        System.out.println("Commands:pwd, cd <dir>, ls, touch <file>, mkdir <dir>, exit, help");
    }


}
