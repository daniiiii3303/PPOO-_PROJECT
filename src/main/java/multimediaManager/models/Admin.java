package multimediaManager.models;

import multimediaManager.file.File;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    protected String name;
    protected String password;
    protected String email;
    protected List<File> filesList;

    public Admin(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<File> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<File> filesList) {
        this.filesList = filesList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Admin{");
        sb.append("name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", filesList=").append(filesList);
        sb.append('}');
        return sb.toString();
    }
}
