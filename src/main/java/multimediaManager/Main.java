package multimediaManager;

import multimediaManager.models.FileManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("1");
        FileManager fileManager = new FileManager();
        fileManager.start();
    }
}