package multimediaManager.data;

import multimediaManager.enums.Quality;
import multimediaManager.file.File;
import multimediaManager.models.Admin;
import multimediaManager.models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class ReadData {
    public static Set<Admin> adminsList() {
        Set<Admin> admins = new HashSet<>();
        try {
            BufferedReader b = new BufferedReader(new FileReader("C:\\Users\\Public\\Documents\\Master\\PPOO\\src\\main\\java\\multimediaManager\\Admin.txt"));
            String s;
            while((s = b.readLine() )!= null) {
                Admin admin = new Admin(s.split(" ")[0], s.split(" ")[1], s.split(" ")[2]);
                admins.add(admin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public static Set<User> usersList() {
        Set<User> users = new HashSet<>();
        try {
            BufferedReader b = new BufferedReader(new FileReader("C:\\Users\\Public\\Documents\\Master\\PPOO\\src\\main\\java\\multimediaManager\\User.txt"));
            String s;
            while((s = b.readLine() )!= null) {
                User user = new User(s.split(" ")[0], s.split(" ")[1], s.split(" ")[2]);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<File> fileList() {
        List<File> files = new ArrayList<>();
        try{
            try (BufferedReader b = new BufferedReader(
                    new FileReader("C:\\Users\\Public\\Documents\\Master\\PPOO\\src\\main\\java\\multimediaManager\\File.txt"))) {
                String s;
                DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
                while ((s = b.readLine()) != null) {
                    File file = new File(s.split(" ")[0],
                            Double.parseDouble(s.split(" ")[1]),
                            Boolean.parseBoolean(s.split(" ")[2]),
                            LocalDate.parse(s.split(" ")[3]),
                            LocalDate.parse(s.split(" ")[4]),
                            s.split(" ")[5],
                            Quality.valueOf(s.split(" ")[6].toUpperCase(Locale.ROOT)));
                    files.add(file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
}
