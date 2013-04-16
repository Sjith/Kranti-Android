package repository;

import android.content.Intent;
import android.graphics.Bitmap;

import java.io.*;

public class ImageRepository {
    private final String IMAGE_BASE_DIR = "/sdcard/kranti/";
    private final File baseDir;

    public ImageRepository() {
        this.baseDir = new File(IMAGE_BASE_DIR);
        this.baseDir.mkdirs();
    }

    private Bitmap imageData(Intent imageIntent) {
        return (Bitmap) imageIntent.getExtras().get("data");
    }


    public String storeImage(Intent imageIntent) {
        String fileName = "kranti_issue_" + System.currentTimeMillis() + ".png";
        try {
            Bitmap imageBitmap = imageData(imageIntent);
            ByteArrayOutputStream imageBytes = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 90, imageBytes);
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
