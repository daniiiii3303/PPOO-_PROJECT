package multimediaManager.models;

import multimediaManager.data.ReadData;
import multimediaManager.enums.Quality;
import multimediaManager.exceptions.FileAlreadyExists;
import multimediaManager.file.File;
import multimediaManager.file.audio.Mp3;
import multimediaManager.file.audio.Wav;
import multimediaManager.file.picture.Jpg;
import multimediaManager.file.picture.Png;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public void start() {

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

    private void nextMenu() {
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

    private void adminMenu() {
        System.out.println();
        System.out.println("--------------------------------\n|         FILE MANAGER         |\n" +
                "|   ***you are the admin***    |\n" +
                "--------------------------------");
        System.out.println("           Admin MENU           ");
        System.out.println("1. List all files");
        System.out.println("2. List your files");
        System.out.println("3. Add a file");
        System.out.println("4. Delete a file");
        System.out.println("5. Create a new directory");
        System.out.println("5. Delete a directory");
        System.out.println("5. Statistics");
        System.out.println("6. Your info");
        System.out.println("7. Log out");
        System.out.println("8. Exit");

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
                    listYourFiles();
                    isTrue = continueOnAdminMenu(isTrue);
                }
                case "3" -> {
                    addFile();
                    isTrue = continueOnAdminMenu(isTrue);


                }
            }
        }while(isTrue);

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

    private boolean continueOnAdminMenu(boolean isTrue) {
        System.out.println("Do you want to go to the previous menu? Y/N");
        output = sc.next();
        if(output.equalsIgnoreCase("y")){
            adminMenu();
        }else {
            isTrue = true;
            exit();
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

    private void listYourFiles() {
        System.out.println("Here are your files:");
        int filesNr = 0;
        for (File file : ReadData.fileList()) {
            if( file.getPath().contains(currentUser.getName())) {
                System.out.println(file.getId() + ". " + file);
                filesNr++;
            }
        }
        if(filesNr == 0 ) {
            System.out.println("You have no files");
        }
    }
}
