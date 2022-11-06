package multimediaManager.file.picture;

import multimediaManager.enums.Quality;

import java.time.LocalDate;

public class Png extends Picture{
    public Png(String name, Double size, boolean isDeleted, LocalDate createdDate, LocalDate modifiedDate, String path,
               Quality quality) {
        super(name+".png", size, isDeleted, createdDate, modifiedDate, path, quality);
    }
}
