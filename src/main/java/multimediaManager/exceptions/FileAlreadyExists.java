package multimediaManager.exceptions;

public class FileAlreadyExists extends Exception{
    public FileAlreadyExists() {
    }

    public FileAlreadyExists(String message) {
        super(message);
    }
}
