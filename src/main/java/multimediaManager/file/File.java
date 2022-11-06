package multimediaManager.file;

import multimediaManager.enums.Quality;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class File {
    protected String id;
    protected String name;
    protected Double size; //mb
    protected boolean isDeleted;
    protected LocalDate createdDate;
    protected LocalDate modifiedDate;
    protected String path;
    protected Quality quality;

    public File(String name, Double size, boolean isDeleted, LocalDate createdDate, LocalDate modifiedDate,
                String path, Quality quality) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.size = size;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.path = path;
        this.quality = quality;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return ", name='" + name + '\'' +
                ", size=" + size +
                ", isDeteled=" + isDeleted +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", location=" + path +
                '}';
    }
}
