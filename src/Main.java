import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {

        openZip("/Users/vi/Desktop/Games/savegames/zip.zip", "/Users/vi/Desktop/Games/savegames");
        openProgress("/Users/vi/Desktop/Games/savegames/gameOne.dat");
    }

    private static void openZip(String s, String unZip) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(s))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(s);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                zin.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static GameProgress openProgress(String open) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(open);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gameProgress;
    }
}