package multimediaManager.file.audio;

import multimediaManager.enums.Quality;

import java.time.LocalDate;
import java.util.Date;

public class Wav extends Audio{
    public Wav(String name, Double size, boolean isDeteled, LocalDate createdDate,
               LocalDate modifiedDate, String path, Quality quality) {
        super(name+".wav", size, isDeteled, createdDate, modifiedDate, path, quality);
    }
}
