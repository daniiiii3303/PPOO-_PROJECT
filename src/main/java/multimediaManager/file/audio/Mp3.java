package multimediaManager.file.audio;

import multimediaManager.enums.Quality;

import java.time.LocalDate;
import java.util.Date;

public class Mp3 extends Audio{
    public Mp3(String name, Double size, boolean isDeteled, LocalDate createdDate,
               LocalDate modifiedDate, String path, Quality quality) {
        super(name+".mp3", size, isDeteled, createdDate, modifiedDate, path, quality);
    }
}
