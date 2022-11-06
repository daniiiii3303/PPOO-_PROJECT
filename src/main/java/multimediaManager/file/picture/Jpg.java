package multimediaManager.file.picture;

import multimediaManager.enums.Quality;

import java.time.LocalDate;
import java.util.Date;

public class Jpg extends Picture{
    public Jpg(String name, Double size, boolean isDeleted, LocalDate createdDate, LocalDate modifiedDate, String path,
               Quality quality) {
        super(name+".jpg", size, isDeleted, createdDate, modifiedDate, path, quality);
    }
}
