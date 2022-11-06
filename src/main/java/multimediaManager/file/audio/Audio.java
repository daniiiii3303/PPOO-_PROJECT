package multimediaManager.file.audio;

import multimediaManager.enums.Quality;
import multimediaManager.file.File;

import java.time.LocalDate;

public class Audio extends File {
//    protected Double duration;
//    protected boolean isEncoded;


    public Audio( String name, Double size, boolean isDeleted, LocalDate createdDate, LocalDate modifiedDate,
                 String path, /*Double duration, boolean isEncoded,*/ Quality quality) {
        super( name, size, isDeleted, createdDate, modifiedDate, path, quality);
//        this.duration = duration;
//        this.isEncoded = isEncoded;
    }
}
