package multimediaManager.file.picture;

import multimediaManager.enums.Quality;
import multimediaManager.file.File;

import java.time.LocalDate;
import java.util.Date;

public class Picture extends File {

    public Picture(String name, Double size, boolean isDeleted, LocalDate createdDate, LocalDate modifiedDate,
                   String path, Quality quality) {
        super(name, size, isDeleted, createdDate, modifiedDate, path, quality);
    }
}
