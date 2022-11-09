package multimediaManager;

import multimediaManager.data.ReadData;
import multimediaManager.file.File;
import multimediaManager.models.User;

import java.util.List;
import java.util.Set;

public class Statistics {
    Set<Path> paths = ReadData.pathList();
    List<File> files = ReadData.fileList();
    Set<User> users = ReadData.usersList();

    public void allOccupiedStorage() {
        double[] storageArray = new double[files.size()+2];
        int i = -1;
        double sum = 0;
        for(File f : files){
            if(!f.isDeleted()) {
                storageArray[++i] = f.getSize();
                sum = sum + f.getSize();
            }
        }
        i++;
        storageArray[i] = sum;
        for(int j =0; j<i; j++) {
            System.out.print(storageArray[j] + " ");
        }
        System.out.println();
    }

    public void returnNbOfUsers() {
        System.out.println("Number of users: "+users.size());
    }
    public void returnTotalNbOfFiles() {
        System.out.println("Number of files: " + files.size());
    }

    public void returnNrOfDeletedFiles() {
        int nr = 0;
        for(File f : files) {
            if(f.isDeleted()){
                nr++;
            }
        }
        System.out.println("Number of deleted files: " + nr);
    }

    public void returnNbOfTypesOfFile() {
        String[][] fileTypes = new String[2][4];
        fileTypes[0][0] = "jpg";
        fileTypes[0][1] = "png";
        fileTypes[0][2] = "wav";
        fileTypes[0][3] = "mp3";
        int jpg=0;
        int png=0;
        int wav=0;
        int mp3=0;
        for(File f : files) {
            if(f.getName().contains("jpg")) {
                jpg++;
            }else if (f.getName().contains("png")){
                png++;
            }else if (f.getName().contains("wav")){
                wav++;
            }else if (f.getName().contains("mp3")){
                mp3++;
            }
        }
        fileTypes[1][0] = String.valueOf(jpg);
        fileTypes[1][1] = String.valueOf(png);
        fileTypes[1][2] = String.valueOf(wav);
        fileTypes[1][3] = String.valueOf(mp3);

        for(int i =0; i<2; i++){
            for(int j=0;j<4;j++){
                System.out.print(fileTypes[i][j] + " ");
            }
            System.out.println();
        }
    }

}
