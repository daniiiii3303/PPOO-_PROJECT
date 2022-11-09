package multimediaManager.models;

import multimediaManager.Statistics;
import multimediaManager.data.ReadData;
import multimediaManager.enums.Quality;
import multimediaManager.exceptions.FileAlreadyExists;
import multimediaManager.file.File;
import multimediaManager.file.audio.Mp3;
import multimediaManager.file.audio.Wav;
import multimediaManager.file.picture.Jpg;
import multimediaManager.file.picture.Png;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class FileManager {
    Scanner sc = new Scanner(System.in);
    Set<User> users = ReadData.usersList();
    Set<Admin> admins = ReadData.adminsList();
    List<File> files = ReadData.fileList();
    Admin currentAdmin = null;
    User currentUser = null;
    String output;
    String option;
    String startOption;
    String status;

    public void start() throws IOException {

        System.out.println();
        System.out.println("--------------------------------\n|         FILE MANAGER         |\n" +
                "--------------------------------");
        System.out.println();
        System.out.println("              MENU              ");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("4. Exit");
        nextMenu();
        if(status.equalsIgnoreCase("user")) {
            userMenu();
        }else {
            adminMenu();
        }

    }

    private void nextMenu() throws IOException {
        boolean isTrue = false;
       do {
            startOption = sc.next();
            switch(startOption) {
                case "1":
                    isTrue = true;
                    register();
                    start();
                case "2":
                    isTrue = true;
                    login();
                    if(status.equalsIgnoreCase("user")){
                        userMenu();
                    }else {
                        adminMenu();
                    }
                case "3":
                    isTrue = true;
                    exit();
                default:
                    System.out.println("Choose a valid option");
                    break;
            }

        }while(!isTrue);
    }
    private void register() {
        System.out.println("Enter your name\n");
        String name = sc.next();
        System.out.println("Enter your password\n");
        String password = sc.next();
        System.out.println("Enter your email\n");
        String email = sc.next();

        User user = new User(name, password, email);
        users.add(user);
        try {
            PrintWriter writer = new PrintWriter(
                    new FileWriter("C:\\Users\\Public\\Documents\\Master\\PPOO\\src\\main\\java\\multimediaManager\\User.txt",
                            true));
            writer.append("\n");
            writer.append(user.getName()).append(" ").append(user.getPassword()).append(" ").append(user.getEmail());
            writer.close();

            String path = "C:\\Users\\Public\\Documents\\Master\\PPOO\\" + name + "\\";
            java.io.File file = new java.io.File(path);
            boolean bool = file.mkdir();
            if(bool){
                System.out.println("Your path is ready: "+" "+path+ "\nHere you can create/delete folders and files");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        System.exit(0);
    }

    private void login() {
        boolean isLogged = false;
        do {
            System.out.println("\n Enter your email\n");
            String email = sc.next();
            System.out.println("\n Enter your password");
            String password = sc.next();

            for(User user: users) {
                if(email.equalsIgnoreCase(user.getEmail()) && password.equalsIgnoreCase(user.getPassword())) {
                    System.out.println("You are logged as: "+ user.getEmail());
                    isLogged = true;
                    currentUser = user;
                    status = "user";
                }
            }
            for(Admin admin: admins) {
                if(email.equalsIgnoreCase(admin.getEmail()) && password.equalsIgnoreCase(admin.getPassword())) {
                    System.out.println("You are logged as: " + admin.getEmail());
                    isLogged = true;
                    currentAdmin = admin;
                    status = "admin";
                }
            }
            if(!isLogged) {
                System.out.println("Login failed. Try again!");
            }
        }while(!isLogged);
    }

    private void userMenu() {

    }

    private void adminMenu() throws IOException {
        System.out.println();
        System.out.println("--------------------------------\n|         FILE MANAGER         |\n" +
                "|   ***you are the admin***    |\n" +
                "--------------------------------");
        System.out.println("           Admin MENU           ");
        System.out.println("1. List all files");
        System.out.println("2. List admin files");
        System.out.println("3. Add a file");
        System.out.println("4. Delete a file");
        System.out.println("5. Find a file");
        System.out.println("6. Open a file");
        System.out.println("7. Create a new directory");
        System.out.println("8. Delete a directory");
        System.out.println("9. Statistics");
        System.out.println("10. Your info");
        System.out.println("11. Log out");
        System.out.println("12. Exit");

        System.out.println("What you want to do? Choose 1-6 options");
        boolean isTrue = false;
        do {
            option = sc.next();
            switch (option) {
                case "1" -> {
                    listAllFiles();
                    isTrue = continueOnAdminMenu(isTrue);
                }
                case "2" -> {
                    listAdminFiles();
                    isTrue = continueOnAdminMenu(isTrue);
                }
                case "3" -> {
                    addFile();
                    isTrue = continueOnAdminMenu(isTrue);
                }
                case "4" -> {
                    deleteFileAsAdmin();
                    isTrue = continueOnAdminMenu(isTrue);
                }
                case "5" -> {
                    findAFile();
                    isTrue = continueOnAdminMenu(isTrue);
                }
                case "6" -> {
                    openAFile();
                    isTrue = continueOnAdminMenu(isTrue);
                }
                case "7" -> {
                    createDirectoryAdmin();
                    isTrue = continueOnAdminMenu(isTrue);
                }
                case "8" -> {
                    deleteDirectoryAdmin();
                    isTrue = continueOnAdminMenu(isTrue);
                }
                case "9" -> {
                    getStatistics();
                    isTrue = continueOnAdminMenu(isTrue);
                }
                case "10" -> {
                    getYourInfo();
                    isTrue = continueOnAdminMenu(isTrue);
                }
                case "11" -> {
                    login();
                    if (status.equalsIgnoreCase("user")) {
                        userMenu();
                    } else {
                        adminMenu();
                    }
                    isTrue = true;
                }
                case "12" -> {
                    exit();
                }
                default -> {
                    System.out.println("Please enter a valid choice");
                    isTrue = continueOnAdminMenu(isTrue);
                }

            }
        }while(isTrue);
    }

    private void logout() {

    }

    private void getYourInfo() {
        if(currentAdmin!=null) {
            Set<Admin> admins = ReadData.adminsList();
            for(Admin admin : admins) {
                System.out.println(admin.toString());
            }
        } else if (currentUser!=null){
            Set<User> users = ReadData.usersList();
            for(User user : users) {
                System.out.println(user.toString());
            }
        }
    }

    private void getStatistics() throws IOException {
        System.out.println("Choose what you want to see: ");
        System.out.println("1. Number of users");
        System.out.println("2. Number of files");
        System.out.println("3. Number of deleted files");
        System.out.println("4. Number of types of files");
        System.out.println("5. Occupied storage + total");

        Statistics statistics = new Statistics();
        boolean isTrue = false;
        do {
            String choice = sc.next();
            switch (choice) {
                case "1" -> {
                    statistics.returnNbOfUsers();
                    isTrue = continueOnAdminStatisticsMenu(isTrue);
                }
                case "2" -> {
                    statistics.returnTotalNbOfFiles();
                    isTrue = continueOnAdminStatisticsMenu(isTrue);
                }
                case "3" -> {
                    statistics.returnNrOfDeletedFiles();
                    isTrue = continueOnAdminStatisticsMenu(isTrue);
                }
                case "4" -> {
                    statistics.returnNbOfTypesOfFile();
                    isTrue = continueOnAdminStatisticsMenu(isTrue);
                }
                case "5" -> {
                    statistics.allOccupiedStorage();
                    isTrue = continueOnAdminStatisticsMenu(isTrue);
                }
            }
        }while(isTrue);

    }


    private void deleteDirectoryAdmin() throws IOException {
        System.out.println("Enter the directory name: ");
        String directoryName = sc.next();
        Set<multimediaManager.Path> paths = ReadData.pathList();
        FileWriter writer = new FileWriter("C:\\Users\\Public\\Documents\\Master\\PPOO\\src\\main\\java\\multimediaManager\\Path.txt");
        writer.write("");
        int contor = 0;
        for(multimediaManager.Path p :paths) {
            if(p.getName().contains(directoryName)) {
                System.out.println("This directory was deleted");
                p.setName(p.getName().replace(directoryName,""));
                writer.append(p.getName()).append("\n");
                contor++;
            } else {
                writer.append(p.getName()).append("\n");
            }
        }
        if(contor==0) {
            System.out.println("This directory doesn't exist");
        }
        writer.close();
    }

    private void createDirectoryAdmin() throws IOException {
        System.out.println("Enter the path: ");
        multimediaManager.Path path = new multimediaManager.Path();
        path.setName(sc.next());
        Set<multimediaManager.Path> paths = ReadData.pathList();
        int contor = 0;
        for(multimediaManager.Path p : paths) {
            if(p.getName().equals(path.getName())){
                contor++;
            }
        }
        if(contor == 0) {
            PrintWriter writer = new PrintWriter(
                    new FileWriter("C:\\Users\\Public\\Documents\\Master\\PPOO\\src\\main\\java\\multimediaManager\\Path.txt", true));
            Files.createDirectories(Paths.get(path.getName()));
            writer.append(path.getName()).append("\n");
            writer.close();
        } else {
            System.out.println("This path already exists");
        }
    }

    private void openAFile() {
        System.out.println("What file do you want to open?");
        String filename = sc.next();
        List<File> files = ReadData.fileList();
        int contor = 0;
        for(File f : files) {
            if(f.getName().contains(filename)) {
                System.out.println("You opened " + f.getName());
                contor++;
            }
        }
        if(contor==0){
            System.out.println("There is no file named "+filename);
        }
    }

    private void findAFile() {
        System.out.println("Enter de file name");
        String filename = sc.next();
        List<File> files = ReadData.fileList();
        int contor = 0;
        for(File f : files){
            if(f.getName().contains(filename)) {
                System.out.println(f.getPath());
                contor++;
            }
        }
        if(contor==0) {
            System.out.println("There is no file named "+filename);
        }
    }

    private void deleteFileAsAdmin() throws IOException {
        System.out.println("You can delete any file because you are the admin\nWhat file you to delete?");
        String fileName = sc.next();
        List<File> files = ReadData.fileList();
        FileWriter writer = new FileWriter("C:\\Users\\Public\\Documents\\Master\\PPOO\\src\\main\\java\\multimediaManager\\File.txt");
        System.out.println(files.size());
        writer.write("");
        int contor = 0;
        for(File f : files) {
            if(f.getName().contains(fileName)){
                System.out.println("This file was deleted! ");
                f.setDeleted(true);
                writer.append(f.getName()).append(" ").append(f.getSize().toString()).append(" ")
                        .append(String.valueOf(f.isDeleted())).append(" ").append(f.getCreatedDate().toString())
                        .append(" ").append(f.getModifiedDate().toString()).append(" ").append(f.getPath())
                        .append("\\").append(f.getName())
                        .append(" ").append(String.valueOf(f.getQuality())).append("\n");
                contor++;
            } else {
                writer.append(f.getName()).append(" ").append(f.getSize().toString()).append(" ")
                        .append(String.valueOf(f.isDeleted())).append(" ").append(f.getCreatedDate().toString())
                        .append(" ").append(f.getModifiedDate().toString()).append(" ").append(f.getPath())
                        .append("\\").append(f.getName())
                        .append(" ").append(String.valueOf(f.getQuality())).append("\n");
            }
        }
        if(contor==0){
            System.out.println("This file doesn't exist");
        }
        writer.close();

    }

    private void addFile() {
        System.out.println("Enter the path: ");
        String path = sc.next();
        System.out.println("Enter the file type: \nAudio: mp3, wav\nPicture: jpg, png");
        String fileType = sc.next();
        System.out.println("Enter the filename: ");
        String filename = sc.next();
        System.out.println("Enter the file size: ");
        Double fileSize = sc.nextDouble();
        System.out.println("Enter the file quality(LD/HD/UHD - default is LD): ");
        String quality1 = sc.next();
        Quality quality = Quality.LD;
        if (quality1 != "LD" || quality1 != "HD" || quality1 != "UHD") {
            quality1 = "LD";
        } else {
           quality = Quality.valueOf(quality1);
        }

        if (fileType.equalsIgnoreCase("mp3")) {
            Mp3 mp3File = new Mp3(filename, fileSize, false, LocalDate.now(),
                    LocalDate.now(), path, quality);
            try {
                createFile(path+"\\"+mp3File.getName());
                System.out.println("Your file was successfully added");
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<Mp3> mp3Files = new ArrayList<>();
            mp3Files.add(mp3File);
            try {
                PrintWriter writer = new PrintWriter(
                        new FileWriter("C:\\Users\\Public\\Documents\\Master\\PPOO\\src\\main\\java\\multimediaManager\\File.txt",
                                true));
                writer.append(mp3File.getName()).append(" ").append(mp3File.getSize().toString()).append(" ")
                        .append(String.valueOf(mp3File.isDeleted())).append(" ").append(mp3File.getCreatedDate().toString())
                        .append(" ").append(mp3File.getModifiedDate().toString()).append(" ").append(mp3File.getPath())
                        .append("\\").append(mp3File.getName())
                                .append(" ").append(String.valueOf(mp3File.getQuality())).append("\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (fileType.equalsIgnoreCase("wav")) {
            Wav wavFile = new Wav(filename, fileSize, false, LocalDate.now(),
                    LocalDate.now(), path, quality);
            try {
                createFile(path+"\\"+wavFile.getName());
                System.out.println("Your file was successfully added");
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<Wav> wavFiles = new ArrayList<>();
            wavFiles.add(wavFile);
            try {
                PrintWriter writer = new PrintWriter(
                        new FileWriter("C:\\Users\\Public\\Documents\\Master\\PPOO\\src\\main\\java\\multimediaManager\\File.txt",
                                true));
                writer.append(wavFile.getName()).append(" ").append(wavFile.getSize().toString()).append(" ")
                        .append(String.valueOf(wavFile.isDeleted())).append(" ").append(wavFile.getCreatedDate().toString())
                        .append(" ").append(wavFile.getModifiedDate().toString()).append(" ").append(wavFile.getPath())
                        .append("\\").append(wavFile.getName())
                        .append(" ").append(String.valueOf(wavFile.getQuality())).append("\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (fileType.equalsIgnoreCase("Jpg")) {
            Jpg jpgFile = new Jpg(filename, fileSize, false, LocalDate.now(),
                    LocalDate.now(), path, quality);
            try {
                createFile(path+"\\"+jpgFile.getName());
                System.out.println("Your file was successfully added");
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<Jpg> jpgFiles = new ArrayList<>();
            jpgFiles.add(jpgFile);
            try {
                PrintWriter writer = new PrintWriter(
                        new FileWriter("C:\\Users\\Public\\Documents\\Master\\PPOO\\src\\main\\java\\multimediaManager\\File.txt",
                                true));
                writer.append(jpgFile.getName()).append(" ").append(jpgFile.getSize().toString()).append(" ")
                        .append(String.valueOf(jpgFile.isDeleted())).append(" ").append(jpgFile.getCreatedDate().toString())
                        .append(" ").append(jpgFile.getModifiedDate().toString()).append(" ").append(jpgFile.getPath())
                        .append("\\").append(jpgFile.getName())
                        .append(" ").append(String.valueOf(jpgFile.getQuality())).append("\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (fileType.equalsIgnoreCase("png")) {
            Png pngFile = new Png(filename, fileSize, false, LocalDate.now(),
                     LocalDate.now(), path, quality);
            try {
                createFile(path+"\\"+pngFile.getName());
                System.out.println("Your file was successfully added");
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<Png> pngFiles = new ArrayList<>();
            pngFiles.add(pngFile);
            try {
                PrintWriter writer = new PrintWriter(
                        new FileWriter("C:\\Users\\Public\\Documents\\Master\\PPOO\\src\\main\\java\\multimediaManager\\File.txt",
                                true));
                writer.append(pngFile.getName()).append(" ").append(pngFile.getSize().toString()).append(" ")
                        .append(String.valueOf(pngFile.isDeleted())).append(" ").append(pngFile.getCreatedDate().toString())
                        .append(" ").append(pngFile.getModifiedDate().toString()).append(" ").append(pngFile.getPath())
                        .append("\\").append(pngFile.getName())
                        .append(" ").append(String.valueOf(pngFile.getQuality())).append("\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createFile(String path) throws IOException {
        Path path1 = Paths.get(path);
        Files.createDirectories(path1.getParent());

        try {
            Files.createFile(path1);
        } catch (FileAlreadyExistsException e) {
            System.err.println("This file already exists: " + e.getMessage());
        }

    }

    private boolean continueOnAdminMenu(boolean isTrue) throws IOException {
        System.out.println("Do you want to go to the previous menu? Y/N");
        output = sc.next();
        if(output.equalsIgnoreCase("y")){
            adminMenu();
        }else if (!output.equalsIgnoreCase("n")){
            continueOnAdminMenu(isTrue);
        }
        else {
            isTrue = true;
            exit();
        }
        return isTrue;
    }

    private boolean continueOnAdminStatisticsMenu(boolean isTrue) throws IOException {
        System.out.println("Do you want to go to the previous menu? Y/N");
        output = sc.next();
        if(output.equalsIgnoreCase("y")){
            getStatistics();
        }else if (!output.equalsIgnoreCase("n")){
            continueOnAdminStatisticsMenu(isTrue);
        } else {
            continueOnAdminMenu(isTrue);
        }
        return isTrue;
    }


    private void listAllFiles() {
        System.out.println("Here are all files:");
        int filesNr = 0;
        for (File file : ReadData.fileList()) {
            System.out.println(file.getId() + ". " + file);
            filesNr++;
        }
        if (filesNr == 0 ) {
            System.out.println("There is no files");
        }
    }


    private void listYourFilesUser() {
        System.out.println("Here are your files:");
        int filesNr = 0;
        for (File file : ReadData.fileList()) {
            if( file.getPath().contains(currentUser.getName()) && !file.isDeleted()) {
                System.out.println(file.getId() + ". " + file);
                filesNr++;
            }
        }
        if(filesNr == 0 ) {
            System.out.println("You have no files");
        }
    }

    private void listAdminFiles() {
        System.out.println("Here are admin files:");
        int filesNr = 0;
        for (File file : ReadData.fileList()) {
            if(file.getPath().contains("Admin")) {
                System.out.println(file.getId() + ". " + file);
                filesNr++;
            }
        }
        if(filesNr == 0 ) {
            System.out.println("You have no files");
        }
    }
}
