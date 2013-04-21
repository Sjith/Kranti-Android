package repository;

import android.graphics.Bitmap;

import java.io.*;

public class ImageRepository {
    private final String IMAGE_BASE_DIR = "/sdcard/kranti/";
    private final File baseDir;

    public ImageRepository() {
        this.baseDir = new File(IMAGE_BASE_DIR);
        this.baseDir.mkdirs();
    }

    public String storeImage(Bitmap image) {
        String fileName = "kranti_issue_" + System.currentTimeMillis() + ".png";
        try {
            ByteArrayOutputStream imageBytes = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 90, imageBytes);
            File outputFile = new File(baseDir, fileName);
            outputFile.createNewFile();
            FileOutputStream stream = new FileOutputStream(outputFile);
            stream.write(imageBytes.toByteArray());
            return outputFile.getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
